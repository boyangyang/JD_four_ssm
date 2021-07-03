import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.junit.Test;
import com.qiniu.storage.Configuration;



public class TestQiNiu {

    //上传文件
    @Test
    public void uploadFile() {

        Configuration cfg = new Configuration(Zone.zone0());

        UploadManager uploadManager = new UploadManager(cfg);

        String AccessKey = "NdptPP3vUWYIrMzVX7tqcTVURhzsjMm--dQVyBfU";
        String SecretKey = "KhQpNJFOQK29YhHvVBvSCdJ_rHU_HO4ZrLcEJl_8";

        String bucket = "01yangyang";

        String localFilePath = "D:\\360MoveData\\Users\\Administrator\\Desktop\\微信图片_20180905162025.jpg";

        String key = null;
        Auth auth = Auth.create(AccessKey, SecretKey);

        String upToken = auth.uploadToken(bucket);

        try {
            Response response = uploadManager.put(localFilePath, key, upToken);

            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);

            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (
                QiniuException e) {
            Response r = e.response;
            System.err.println(toString());

            try {
                System.err.println(r.bodyString());
            } catch (QiniuException qiniuException) {

            }
        }
    }

    //删除文件
    @Test
    public void deleteFile() {
        Configuration cfg = new Configuration(Zone.zone0());

        String AccessKey = "NdptPP3vUWYIrMzVX7tqcTVURhzsjMm--dQVyBfU";
        String SecretKey = "KhQpNJFOQK29YhHvVBvSCdJ_rHU_HO4ZrLcEJl_8";

        String bucket = "01yangyang";

        String key = "Fr6z8JGwUvXR80sgVAMEJIiZ3CDa";

        Auth auth = Auth.create(AccessKey, SecretKey);

        BucketManager bucketManager = new BucketManager(auth, cfg);

        try {
            bucketManager.delete(bucket, key);
        } catch (QiniuException e) {
            System.err.println(e.code());
            System.err.println(e.response.toString());
        }
    }
}