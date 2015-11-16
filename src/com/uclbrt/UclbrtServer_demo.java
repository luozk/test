package com.uclbrt;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.omg.CORBA_2_3.portable.OutputStream;

import ytx.org.apache.http.HttpEntity;
import ytx.org.apache.http.HttpResponse;
import ytx.org.apache.http.client.methods.HttpPost;
import ytx.org.apache.http.client.methods.HttpRequestBase;
import ytx.org.apache.http.entity.BasicHttpEntity;
import ytx.org.apache.http.impl.client.DefaultHttpClient;
import ytx.org.apache.http.params.CoreConnectionPNames;
import ytx.org.apache.http.params.CoreProtocolPNames;
import ytx.org.apache.http.util.EntityUtils;

/**
 * demo for uclbrt
 * @author candy
 *
 */
public class UclbrtServer_demo {

	/**
	 * @param protocol
	 * @param ip
	 * @param port
	 * @param communityNo
	 * @param buildNo
	 * @param floorNo
	 * @param roomNo
	 * @param acc
	 * @param token
	 * @return
	 */
	public String getFunc(String protocol, String ip, String port,
			String communityNo, String buildNo, String floorNo, String roomNo,
			String acc, String token) {

		UclbrtHttpClient chc = new UclbrtHttpClient();
		DefaultHttpClient httpclient = null;
		try {
			long regTime = System.currentTimeMillis();
			httpclient = chc.registerSSL(ip, "TLS", Integer.parseInt(port),
					protocol);
			System.out.println("reg time : "+(System.currentTimeMillis()-regTime));
		} catch (Exception e1) {
			throw new RuntimeException("init httpclient exception");
		}
		String result = "";
		try {
			HttpPost httppost = (HttpPost) getFuncHttpRequestBase(protocol, ip,
					port, communityNo, buildNo, floorNo, roomNo, acc, token);
			String requsetbody = "";
			BasicHttpEntity requestBody = new BasicHttpEntity();
			requestBody.setContent(new ByteArrayInputStream(requsetbody
					.getBytes("UTF-8")));
			requestBody.setContentLength(requsetbody.getBytes("UTF-8").length);
			httppost.setEntity(requestBody);
			httppost.getParams().setBooleanParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE, false);

			long exTime = System.currentTimeMillis();
			
			//httpclient.getHttpConnectionManager().getParams().setConnectionTimeout(0);
			httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,0);
			httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,0);
			HttpResponse response = httpclient.execute(httppost);
			System.out.println("ex time :"+(System.currentTimeMillis()-exTime));
			
			HttpEntity entity = response.getEntity();
			if (entity != null)
				result = EntityUtils.toString(entity, "UTF-8");
			//System.out.println(result);
			return result;
		} catch (IOException e) {
			throw new RuntimeException("the network exception");
		} catch (Exception e) {
			throw new RuntimeException("there is no value call back");
		} finally {
			if (httpclient != null)
				httpclient.getConnectionManager().shutdown();
		}
	}

	/**
	 * @param protocol
	 * @param ip
	 * @param port
	 * @param communityNo
	 * @param buildNo
	 * @param floorNo
	 * @param roomNo
	 * @param acc
	 * @param token
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	private HttpRequestBase getFuncHttpRequestBase(String protocol, String ip,
			String port, String communityNo, String buildNo, String floorNo,
			String roomNo, String acc, String token)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {

		EncryptUtil eu = new EncryptUtil();
		String sig = acc + token + dateFormat();
		;
		String signature = eu.md5Digest(sig);
		String url = protocol + "://" + ip + ":" + port
				+ "?c=Qrcode&a=get&communityNo=" + communityNo + "&buildNo="
				+ buildNo + "&floorNo=" + floorNo + "&roomNo=" + roomNo
				+ "&sig=" + signature.toUpperCase();
		HttpRequestBase mHttpRequestBase = null;

		mHttpRequestBase = new HttpPost(url);
		mHttpRequestBase.setHeader("Accept", "application/json");
		mHttpRequestBase.setHeader("Content-Type",
				"application/json;charset=utf-8");

		String src = acc + ":" + dateFormat();
		String auth = eu.base64Encoder(src);
		mHttpRequestBase.setHeader("Authorization", auth);
		return mHttpRequestBase;
	}

	public static String dateFormat() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		Date curDate = new Date(System.currentTimeMillis());
		return formatter.format(curDate);
	}
	
	public static String trans2Binary(String s) {
        char[] chars = new char[s.length() / 2];
        int tmp;

        for (int i = 0; i < s.length() / 2; i++) {
            tmp = Integer.parseInt(s.substring(2 * i, 2 * i + 2), 16);
            chars[i] = (char) tmp;
        }
        return String.valueOf(chars);
    }

	/**
	 * main function
	 * @param args
	 */
	public static void main(String[] args) {
		
		long totalTime = 0;
		long[] eleTime = new long[10];

		
		//根据用户所需要的生成条件设置
		String protocol = "https";
		String ip = "120.24.54.2";
		String port = "8058";
		String communityNo = "1316879800";
		String buildNo = "001";
		String floorNo = "001";
		String roomNo = "401G";
		String acc = "6ceb05da4634c54c5480a191f714044e";
		String token = "fc3ddfd962cbea8c0dfa2565c496a9";

		UclbrtServer_demo dds = new UclbrtServer_demo();
		
		//System.setProperty("org.apache.commons.logging.log", "org.apache.commons.logging.impl.Simplelog");
		//System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "true");
		//System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http", "debug");
		
			String joStr = dds.getFunc(protocol, ip, port, communityNo, buildNo, floorNo, roomNo,
					acc, token);
		//System.out.println("eve time : "+totalTime/10.00);
		//保存图片
		/*if(null != joStr){
			joStr = joStr.replace("{", "");
			joStr = joStr.replace("}", "");
			String[] jsonInfo = joStr.split(",");
			Map<String , String> jsonInfoMap = new HashMap<String, String>();
			for(String ji : jsonInfo){
				String[] attriInfo = ji.split(":");
				jsonInfoMap.put(attriInfo[0].replace("\"", ""), attriInfo[1].replace("\"", ""));
			}
			System.out.println(jsonInfoMap.get("status"));
			if(jsonInfoMap.get("status").equals("200")){
				//保存图片文件
				String picString = jsonInfoMap.get("info");
				EncryptUtil eu = new EncryptUtil();
				try {
//					FileWriter writer = new FileWriter("D:\\bbb.png", false);
//		            writer.write(eu.base64Decoder(picString));
//		            writer.close();
//					
//					FileOutputStream fos = new FileOutputStream("D:\\bb1.png");
//					fos.write(eu.base64Decoder(picString).getBytes("utf-8"));
//					fos.close();
//					System.out.println(eu.base64Decoder(picString));
					
//					
//					byte[] b = eu.base64Decoder(picString).getBytes();   
//					//b = "asdflkdjfkdjfd".getBytes();
//					FileOutputStream os = new FileOutputStream("d:\\bb2.png");
//					os.write(b);
//					os.flush();
//					os.close();
					
					//二维码串
					//trans2Binary(eu.base64Decoder(picString));
					
					// call zxing 转成png
				
					
					
					sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
					picString = picString.replace("\\", "");
					// Base64解码
					byte[] b = decoder.decodeBuffer(picString);
					for (int i = 0; i < b.length; ++i) {
					if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
					}
					}
					// 生成jpeg图片
					String imgFilePath = "d://test021.png";// 新生成的图片
					FileOutputStream out = new FileOutputStream(new File(imgFilePath));
					out.write(b);
					out.flush();
					out.close();
					
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}*/
	}
	
	
	public static void mainaa(String[] agrs){
		
		EncryptUtil eu = new EncryptUtil();
		byte[] b;
		try {
//			b = eu.base64Decoder("sdfsdfsdfds").getBytes();
//			b = "asdflkdjfkdjfd".getBytes();
//			FileOutputStream os = new FileOutputStream("d:\\b0.png");
//			os.write(b);
//			os.flush();
//			os.close();
			
			sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
			// Base64解码
			 b = decoder.decodeBuffer("sdfsdfsdfsd");
			for (int i = 0; i < b.length; ++i) {
			if (b[i] < 0) {// 调整异常数据
			b[i] += 256;
			}
			}
			// 生成jpeg图片
			String imgFilePath = "d://test21.png";// 新生成的图片
			FileOutputStream out = new FileOutputStream(new File(imgFilePath));
			out.write(b);
			out.flush();
			out.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		
	}

}