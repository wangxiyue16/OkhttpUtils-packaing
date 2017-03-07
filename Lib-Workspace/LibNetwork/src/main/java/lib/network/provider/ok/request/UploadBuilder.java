package lib.network.provider.ok.request;

import android.os.Environment;

import com.zhy.http.okhttp.builder.OkHttpRequestBuilder;
import com.zhy.http.okhttp.builder.PostFormBuilder;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lib.network.LogNetwork;
import lib.network.bean.NetworkListener;
import lib.network.bean.NetworkRequest;
import lib.network.bean.NetworkRequest.TNetworkMethod;
import lib.network.param.NameByteValuePair;
import lib.network.param.NameFileValuePair;

/**
 * @author WDSG
 */
public class UploadBuilder extends PostBuilder {

    public UploadBuilder(NetworkRequest request, Object tag, int id, NetworkListener listener) {
        super(request, tag, id, listener);
    }

    @Override
    protected OkHttpRequestBuilder initBuilder() {
        PostFormBuilder builder = (PostFormBuilder) super.initBuilder();

        List<NameByteValuePair> byteParams = request().getByteParams();
        if (byteParams != null) {
            for (NameByteValuePair pair : byteParams) {
                builder.addFile(pair.getName(), pair.getName(), DeleteOnExit.getInstance().add(tag(), id(), pair.getValue()));
            }
        }

        List<NameFileValuePair> fileParams = request().getFileParams();
        if (fileParams != null) {
            for (NameFileValuePair pair : fileParams) {
                builder.addFile(pair.getName(), pair.getValue(), new File(pair.getValue()));
            }
        }

        return builder;
    }

    @Override
    public TNetworkMethod method() {
        return TNetworkMethod.upload;
    }

    private static class DeleteUnit {
        public String mPath;
        public Object mTag;
        public int mId;

        public DeleteUnit(Object tag, int id, String path) {
            mTag = tag;
            mId = id;
            mPath = path;
        }
    }

    public static class DeleteOnExit {

        public String KTmpSuffix = ".tmp";
        public String KTmpPrefix = "/network_upload_tmp";


        private List<DeleteUnit> mUnits;

        private String mPrefix;
        private static DeleteOnExit mSelf;

        private DeleteOnExit() {
            mUnits = new ArrayList<>();

            mPrefix = Environment.getExternalStorageDirectory().toString() + KTmpPrefix;
            File file = new File(mPrefix);
            if (!file.exists()) {
                file.mkdirs();
            }
        }

        public static DeleteOnExit getInstance() {
            if (mSelf == null) {
                mSelf = new DeleteOnExit();
            }
            return mSelf;
        }

        public File add(Object tag, int id, byte[] bytes) {
            File file = bytesToFile(bytes, mPrefix, bytes.hashCode() + KTmpSuffix);
            mUnits.add(new DeleteUnit(tag, id, file.getAbsolutePath()));
            return file;
        }

        public void delete(Object tag, int id) {
            if (mUnits.isEmpty()) {
                return;
            }

            for (DeleteUnit unit : mUnits) {
                if (unit.mTag.equals(tag) && unit.mId == id) {
                    File file = new File(unit.mPath);
                    file.delete();
                    break;
                }
            }
        }

        private File bytesToFile(byte[] bytes, String filePath, String fileName) {
            BufferedOutputStream bos = null;
            FileOutputStream fos = null;
            File file = null;
            try {
                File dir = new File(filePath);
                if (!dir.exists() && dir.isDirectory()) {
                    dir.mkdirs();
                }
                file = new File(filePath + File.separator + fileName);
                fos = new FileOutputStream(file);
                bos = new BufferedOutputStream(fos);
                bos.write(bytes);
            } catch (Exception e) {
                LogNetwork.e(e);
            } finally {
                if (bos != null) {
                    try {
                        bos.close();
                    } catch (IOException e) {
                        LogNetwork.e(e);
                    }
                }
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        LogNetwork.e(e);
                    }
                }
            }

            return file;
        }
    }
}
