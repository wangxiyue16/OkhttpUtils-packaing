package lib.network.bean;

/**
 * @author WDSG
 */
public class NetworkConst {

    public class Http {
        public static final String KUserAgent = "User-Agent";

        public static final String KContentLen = "Content-Length";
        public static final String KContentType = "Content-Type";

        /**
         * Encodings
         */
        public static final String KContentEncoding = "Content-Encoding";
        public static final String KAcceptEncoding = "Accept-Encoding";
        public static final String KEncoding_gzip = "gzip";
        public static final String KEncoding_identity = "identity";

        /**
         * Accept-Ranges
         */
        public static final String KAcceptRanges = "Accept-Ranges";
        public static final String KAcceptRanges_bytes = "bytes";

        public static final String KContentRanges = "Content-Range";
    }

    public class ContentType {
        public static final String KStream = "application/octet-stream";
    }
}
