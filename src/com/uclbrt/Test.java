package com.uclbrt;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Test {

	public static void main(String[] args) {
		//String strImg = GetImageStr();
		//System.out.println(strImg);
		GenerateImage("");
	}

	// 图片转化成base64字符串
	public static String GetImageStr() {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		String imgFile = "d:/20150619060152.png";// 待处理的图片
		InputStream in = null;
		byte[] data = null;
		// 读取图片字节数组
		try {
			in = new FileInputStream(imgFile);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);// 返回Base64编码过的字节数组字符串
	}

	// base64字符串转化成图片
	public static boolean GenerateImage(String imgStr) { // 对字节数组字符串进行Base64解码并生成图片
		if (imgStr == null) // 图像数据为空
			return false;
		//java
		imgStr = "iVBORw0KGgoAAAANSUhEUgAAAKQAAACkCAMAAAAua3VzAAAAOVBMVEX///8XWrgXWrgXWrgXWrgXWrgXWrgXWrgXWrgXWrgXWrgXWrgXWrgXWrgXWrgXWrgXWrgAAAD////LDwt7AAAAEHRSTlMARCIAVLvd72YQmc2riXYy4ZkgLwAAAvtJREFUeJzt1+F2ojAQBeBAIAioHfv+D7uAnh4c52awjWfxeO+PIiHMfIks24bvN0j434AtIbJUiCwVIkuFyFIhslSILBUiS+UBeclkPcc6ojF07vUhkkirmDWuP1toPZ5bmNefSCJRIw+NYOh8Sx8iifRuRo2to4XeMo9IIp9Bonn6Wi5EEvkbpIffArWu5TBEEomQKFaTvxxzfYgkcmtQM73IZ+vmQmSpvC/SamRBtoKt69b9RBJpIa3G1jUPiRaK6lvziSRSY3RxDbbOc/HAVm8iiURF1wU8LDqiz15fIj8bibC5prl7UZ3c5/LI4ITId0DeQdTY3fkvkd6RSCIt2PoIH/gNSA+VA34Ysqrnn/EnV15T7wrZViFImtLFKEsOMo10r0SiscsqP+ch9DOyvuJiTNK2kpquCdKs/uF4i82NFUBWkqRLKYRB6vm7nqASxrFNaTzuCDnvZAq9jMsDOe9kSMu+tjtC1mNTpySzsL/t5PREyt178iVIE2Tke0LOuW6dDA9I3cDoA6aURV6/7i6equE0CadNvcJFzvtBDl07pEF6icfzTnfycNu19iCxPdyQ/UuRViF0bcntZZ5OQY4xVbGSdn42m9SnXv2CoRf9UAv0K4WsJXxNr8kJOe+p9GF+mbc7Q4bQTU9kkj5OL/RevuaXe9/KaUfINP0H2I3xLMu7MtZy7pavOr1oJ62JusBdI+9XtSfqE0mkhbQK5o5b/xDTi9WLJpJIhLyo6OIPWA+pF2XUQrhySDWmP+v5RO4KaTWxCnrAHBoB0TwiPxupH259rsf0PAtq1UD9iCQSXdAoDfHAqJY3bo0R+dlIqylqbCW3KHRPbhOIJNLLFvzWezUc3U/kZyMvmWxFrxuiOhYK9SCSSITxzhEEoYgk8hlkrqHVFDWz6qAgKJFEekgLnFtEDqrvJ5LIZ5E5jAXWdbwaRBKJkB4eIS5GvEWinkQSiZpYMDQfLQT1sT4TSeTeQ2SpEFkqRJYKkaVCZKkQWSpElspbIP8BXsoFnFPXKLUAAAAASUVORK5CYII=";
		//imgStr = "iVBORw0KGgoAAAANSUhEUgAAAKQAAACkCAMAAAAua3VzAAAAOVBMVEX///8XWrgXWrgXWrgXWrgXWrgXWrgXWrgXWrgXWrgXWrgXWrgXWrgXWrgXWrgXWrgXWrgAAAD////LDwt7AAAAEHRSTlMARCIAVLvd72YQmc2riXYy4ZkgLwAAAwdJREFUeJzt1uF2mzAMBWCDwQRIUrXv/7AzJMth6pXtNs4ZOVz9GGBs6ZMDdO7rDcL9b0BJEFkriKwVRNYKImsFkbWCyFpBZK34hvxMxPa+tebvdepYUodIIreL9Rgat+Y9e59IIq1JuZchlxzlsfKhcSKJfBa5Xa/XEUnkK5F6nsZZWJSfSCJ/gkzh9Tx0rgun1ufqE0mkFSnsb46pOkQSWRrbJKgBq5A1XhJEErktqotrFAJrTCqfbgA1QuSxkboIgqSAJYgcikgiNRJdo2QWGDVrNVRSh8hjI3UB6xwV1XNT+XQuqykiibSSWdAcTN/Tc9B5faTLBJF7RloJLXgpEq1PbQaRRCKkBsOEOeTXvy8bOlq4AyKbdvnXP+LG69pdIfvGOQkxBu9ljZPEkeHVSLQIJnRuXJDtDed9kL6X0A2dk069ONYG5LAVkI0EGUJwbpJ2+a0jVNw89yHM5x0hl50MbpR5fSCXnXRh3dd+R8h27toQZBGO952MT6R8+05WRaJF6Ho9RuQSt62TCSERQGMQriry9nMP/tJMlyiMm3qDi1z3g5yGfgqTjOLP153u5Om+a/1JfH+6I8eXIjXCOn9UvX/Mw8XJ2YfGN9Ivz2YXxjDeP+a6cZgHNFIb2Yr7iJ/JiFz2VEa3fMz7nSGdG+ITGWT08YM+ysfycR97uewIGeIfwGH2V1m/lb6V67D+1OGFO4kmIvx6XfhfNQuWy08kkd8QBviRtACZaj5Xn0giNST1kBch1bpUXiueR37it9JaR+RbILdjCGiBdE4rTwpL5LGRGocg2zFdpKQ5a1OIJBIhUdFUAzq5NT+HS80j8thIq2iqOFqj72mM1SyCEnlsZC62CRE6BSzdBCKJzBXUha2iei5ClB6JJBIVReMIlSuG5uXqEUkkwpQmR3P1GJqL1hBJ5E+RucQab+XVaLQRRBJZgtT3UngrUH7UGJFEWpMsvAW3mknNQTWJJLI0MZqPmtw2ZjVsNUgkkXsOImsFkbWCyFpBZK0gslYQWSuIrBVvgfwDeFUFEQ3PzHYAAAAASUVORK5CYII=";
		//php
		//imgStr = "iVBORw0KGgoAAAANSUhEUgAAAKQAAACkCAMAAAAua3VzAAAAOVBMVEX///8XWrgXWrgXWrgXWrgXWrgXWrgXWrgXWrgXWrgXWrgXWrgXWrgXWrgXWrgXWrgXWrgAAAD////LDwt7AAAAEHRSTlMARCIAVLvd72YQmc2riXYy4ZkgLwAAAvNJREFUeJzt1W17qjAMBuBCoQioy/b/f+wpqBuLeam76hm7fPJhIDbJnQosfPyBCL8NKAkgawWQtQLIWgFkrQCyVgBZK4CsFXfIdyNu3/Pj7dzK52u9PkACuW3Cr/HrFtir80gfIIHkDfmNrh2lwt6D4fUBEshHkLyQtFaqAySQz0DyNVoOz7dygQSyBGnht8X5uTWwVKekP5BAaqFhf3q0+gAJZGl4zfg6vubRfkACWQLh32/XSEipLq8j1QQSSKkBT7IKakNoA2oBJJAakDfSrnsIbSOszQASSK+5V1wbQsuRBtCGAhJID2ENoOXx9V5eXWRwAsg9I6WQgF8VfOS7AOXYu7pAAukApUbrZw/pDMyvvTiyaZe/8TMuvK7dFbJvQqCUY4iR1jhQvjI8G1lSbJWGMC7I9oKLMVHfU+qGLlC3eXCkB6T04amAbCjRkFIIE7XLb52hFOa5T2k+7gi57GQKI83rDbnsZEjrvvY7QrZz16ZEi3C87mS+I+nbe7IqkifwuJsmI5e4bB1NGlJDWbiqyMvPPcRTM52yMG/qBU503g9yGvopTTRSPJ53upOH6671B4r94Yocn4osKbYtenuZp1OgY0xNbKhf7s0ujWm8IrXmW5gFrYVsKbzl12RGLntKY1he5v3OkCEM+Y5MNMb8Qh/pbXm5jz2ddoRM+R/gMMczre/K2NJ5WH/q9J92UorvFZzYDqQMzXsBCaSE1BLu1hUgpYGtTQESyBIob/JZrBApgbZHfl4XaeRKjYHcHfInIAmjgaThtfpAAqklaQ1KcrwBvbVAvjZym8yblxQsqSPV0AYFEkiOkkJrYn0uqanlAfnaSC+kISSABHpkGCCBlBppTTyohLCOUh8ggdSa1GhmIaRNABJIDeMlewgPW1ofSCAtJC9mfZbOpToeFEggLaS1VsvXrllgIIG0FnnNJagVfL3VF0ggveJaUQnMUSWDAwmkdHFvAWStALJWAFkrgKwVQNYKIGsFkLXiTyD/Aa+mBSLzARKEAAAAAElFTkSuQmCC";
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] b = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			// 生成jpeg图片
			String imgFilePath = "d://aaa5.png";// 新生成的图片
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}