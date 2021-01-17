package youdao;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
public class YoudaoNoteSingle {
	private static String mTitle = null; private static final String PREFIX = "C:\\Users\\i042416\\Pictures\\";
	private static void createFolder(String title){ DownloadTask.FOLDER = PREFIX + title; File file = new File( DownloadTask.FOLDER); file.mkdir();}
	private static List<DownloadTask> getYoudaoPicUrlList(String formattedUrl){
	HttpClient client = HttpClients.createDefault(); List<DownloadTask> resultPic = new ArrayList<DownloadTask>(); int index = 0;
	HttpGet get = new HttpGet(formattedUrl); try {
	HttpResponse response = client.execute(get); HttpEntity entity = response.getEntity();
String result = EntityUtils.toString(entity, "UTF-8"); JSONObject obj = new JSONObject(result);
mTitle = obj.get("tl").toString(); createFolder(mTitle); String content = obj.get("content").toString();
Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(content);
while (m.find()) { DownloadTask task = new DownloadTask(m.group(1), index++); resultPic.add(task); } } catch (Exception e){ e.printStackTrace(); }
return resultPic; } private static void start(List<DownloadTask> task){
if( task.isEmpty()){System.out.println("No picture to download!"); return;}
System.out.println("Total pic to be downloaded: " + task.size()); ExecutorService executor = Executors.newFixedThreadPool(10);
for( int i = 0; i < task.size(); i++){PictureDownloader cc = new PictureDownloader(task.get(i)); executor.execute(cc);}executor.shutdown();
while (!executor.isTerminated()) {} System.out.println("Download Finished.");} private static void download(){
String zero = "c91a710af51c1e1b20f5d1da2140a9e4"; String one = "4cc557ab9b7cbde0515b49a155c5dce3"; String two = "6eaae532daaa678cc610f2a34cbc9119"; String urlStr = "";
/***************/  int YOUR_CHOICE = 1; /************/ switch(YOUR_CHOICE){
case 0: urlStr = "http://note.youdao.com/yws/public/note/" + zero + "?keyfrom=public"; break;
case 1: urlStr = "http://note.youdao.com/yws/public/note/" + one + "?keyfrom=public"; break;
case 2: urlStr = "http://note.youdao.com/yws/public/note/" + two + "?keyfrom=public"; break; }
System.out.println("url: " + urlStr); List<DownloadTask> task = getYoudaoPicUrlList(urlStr); start(task);}
	public static void main(String[] args) {download(); }}
