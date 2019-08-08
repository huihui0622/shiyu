package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ErrorTest2 {

	/*
	 * 羽你相约
	 * 问君能有几多愁,不如今晚来打球
	 * 发展羽毛球运动,增强全民体质
	 * 
	 * 11*/
	
	public static void main(String[] args) throws Exception {
		String  date ="20190729";
		String wenan ="问君能有几多愁,不如今晚来打球";
		//请求贡献代码
		run(date,wenan);
	}
	
	public static void run(String data ,String wenAn) throws Exception{
		String path = "E:\\视羽\\baoming.txt";
		List<String> scanListPath = readFile02(path);

		ArrayList<Object> returnList = new ArrayList<>();
		ArrayList<Double> returnListint = new ArrayList<>();
		ArrayList<String> subList = new ArrayList<>();

		for (String hang : scanListPath) {
			// System.out.println(hang);
			// 获取等级
			int indexOf = hang.indexOf("【");
			int jibieindex = indexOf + 3;
			String jibie = hang.substring(indexOf + 1, jibieindex + 1);
			// System.out.println("级别："+jibie);

			// 获取昵称
			int indexOfname = hang.indexOf("】");
			String substringname = hang.substring(indexOfname + 1);
			// System.out.println("昵称："+substringname);

			subList.add("【"+jibie+"】"+substringname);
		}
		
		ArrayList<String> sort2 = sort2(subList);
		ArrayList<String> sort3 = new ArrayList<String>();
		for (int i = 0; i < sort2.size(); i++) {
			String string = sort2.get(i);
			int indexOfname = string.indexOf("【");
			int indexOfname2 = string.indexOf("】");
			int length = string.length();
			String string2 =  string.substring(indexOfname2+1);
			string2 = string2.replace("（已）", "");
			sort3.add(string2);
		}
		
		for (String string : sort2) {
			System.out.println(string);
		}
		
		maintest(sort3 , data,wenAn);
	}

	/**
	 * 读取一个文本 一行一行读取
	 *
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static List<String> readFile02(String path) throws IOException {
		// 使用一个字符串集合来存储文本中的路径 ，也可用String []数组
		List<String> list = new ArrayList<String>();
		FileInputStream fis = new FileInputStream(path);
		// 防止路径乱码 如果utf-8 乱码 改GBK eclipse里创建的txt 用UTF-8，在电脑上自己创建的txt 用GBK
		InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		String line = "";
		while ((line = br.readLine()) != null) {
			// 如果 t x t文件里的路径 不包含---字符串 这里是对里面的内容进行一个筛选
			if (line.lastIndexOf("---") < 0) {
				list.add(line);
			}
		}
		br.close();
		isr.close();
		fis.close();
		return list;
	}

	public int[] sort(int[] arr) {
		if (arr == null || arr.length <= 1) {
			return arr;
		}
		int length = arr.length;
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length - i - 1; j++) {
				if (arr[j] < arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
		return arr;
	}
	
	public static ArrayList<String> sort2(ArrayList<String> scanListPath) {
		if (scanListPath == null || scanListPath.size() <= 1) {
			return scanListPath;
		}
		int length = scanListPath.size();
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length - i - 1; j++) {
				if (Double.valueOf(scanListPath.get(j).substring(1, 4)) < Double.valueOf(scanListPath.get(j + 1).substring(1, 4))) {
					//int temp = arr[j];
					String  temp = scanListPath.get(j);
					scanListPath.set(j, scanListPath.get(j + 1));
					//arr[j] = arr[j + 1];
					//arr[j + 1] = temp;
					scanListPath.set(j + 1, temp);
				}
			}
		}
		return scanListPath;
	}
	
	
	
	    public static void maintest(ArrayList<String> list ,String dateString, String wenAnString) throws Exception {
	    	
	    	int changdishu = list.size()/6;
	    	int size = list.size();
	    	int duo = size % 6 ;
	    	if(duo!= 0){
	    		changdishu++;
	    		int jia = 6 - duo ;
	    		for (int i = 0; i < jia; i++) {
	    			list.add("");
				}
	    	}
	    	
	    	ArrayList<Object[]> arrayList = new ArrayList<>();
	    	//初始化首行
	    	Object[] arrinit=new Object[changdishu];
	    	for (int i = 0; i < changdishu; i++) {
	    		arrinit[i] = (i+1)+"号场地";
			}
	    	
	    	Object[] arrDate=new Object[changdishu];
	    	arrDate[0] = dateString;
	    	
	    	//一个场地6个人 构造6行
	    	int kongjiang = 0 ;
	    	for (int y = 0; y < 6; y++) {
	    		Object[] arr=new Object[changdishu];
	    		//一个数组 多个格子
	    		for (int x = 0; x < changdishu; x++) {
	    			int index = y+x*6 ;
	    			String string = list.get(index);
	    			//String replace = string.replace(" ","") ;
	    			if( null != string && !"".equals(string)){
	    				arr[x] = list.get(index);
	    			}else{
	    				arr[x] = "空降";
	    			}
				}
	    		arrayList.add(arr);
			}
	    

	        // Create blank workbook
	       XSSFWorkbook workbook = new XSSFWorkbook();
	       
	        // Create a blank sheet
	        XSSFSheet spreadsheet = workbook.createSheet("视羽俱乐部");

	        // Create row object
	        XSSFRow row;

	        // This data needs to be written (Object[])
	        Map<String, Object[]> empinfo = new TreeMap<String, Object[]>();
	       //empinfo.put("1", new Object[] { "编号", "姓名", "描述" });

	       // empinfo.put("2", new Object[] { "10010", "李立", "技术经理" });

	       // empinfo.put("3", new Object[] { "10011", "张晓龙", "微信它爹" });

	       // empinfo.put("4", new Object[] { "10012", "王小飞", "技术作家" });

	       // empinfo.put("5", new Object[] { "10013", "库里", "NBA球员" });

	       // empinfo.put("6", new Object[] { "10014", "李双双", "体操运动员" });
	        
	        empinfo.put("0",arrDate);
	        empinfo.put("1",arrinit);
	        
	        for (int i = 0; i < arrayList.size(); i++) {
	        	empinfo.put((i+2)+"", arrayList.get(i));
	        	if(i==(arrayList.size()-1)){
	        		Object[] anwen=new Object[changdishu];
	        		anwen[0] = wenAnString;
	        		empinfo.put((i+2+1)+"", anwen);
	        	}
			}
	        
	        

	        // Iterate over data and write to sheet
	        Set<String> keyid = empinfo.keySet();
	        int rowid = 0;

	        for (String key : keyid) {
	            row = spreadsheet.createRow(rowid++);
	            Object[] objectArr = empinfo.get(key);
	            int cellid = 0;

	            for (Object obj : objectArr) {
	                Cell cell = row.createCell(cellid++);
	                cell.setCellValue((String) obj);
					if ("0".equals(key) || "8".equals(key)) {
						XSSFCellStyle style = workbook.createCellStyle();
						XSSFFont font1 = workbook.createFont();
						// 设置字号大小
						font1.setFontHeightInPoints((short) 15);
						// 设置加粗
						font1.setBold(true);
						style.setFont(font1);
						style.setVerticalAlignment(VerticalAlignment.CENTER);
						style.setAlignment(HorizontalAlignment.CENTER);
						style.setBorderBottom(BorderStyle.THIN);
						style.setBorderLeft(BorderStyle.THIN);
						style.setBorderTop(BorderStyle.THIN);
						style.setBorderRight(BorderStyle.THIN);
						cell.setCellStyle(style);     
					}else{
						XSSFCellStyle style = workbook.createCellStyle();
						style.setVerticalAlignment(VerticalAlignment.CENTER);
						style.setAlignment(HorizontalAlignment.CENTER);
						style.setBorderBottom(BorderStyle.THIN);
						style.setBorderLeft(BorderStyle.THIN);
						style.setBorderTop(BorderStyle.THIN);
						style.setBorderRight(BorderStyle.THIN);
						cell.setCellStyle(style);   
					}
	            }
	            spreadsheet.setColumnWidth(Integer.valueOf(key), 15 * 256);
	        }
	        // 合并日期占两行(4个参数，分别为起始行，结束行，起始列，结束列)
	        // 行和列都是从0开始计数，且起始结束都会合并
	        // 这里是合并excel中日期的两行为一行
	        CellRangeAddress region = new CellRangeAddress(0, 0, 0, changdishu-1); 
	        //region.setFirstColumn(0);
	        //region.setFirstRow(0);
	        spreadsheet.addMergedRegion(region);
	        
	        CellRangeAddress region2 = new CellRangeAddress(8, 8, 0, changdishu-1); 
	        spreadsheet.addMergedRegion(region2);
	       
	        
	        // Write the workbook in file system
	        FileOutputStream out = new FileOutputStream(new File("E:\\视羽\\视羽俱乐部"+dateString+"活动.xlsx"));

	        workbook.write(out);
	        out.close();
	        System.out.println("Writesheet.xlsx written successfully");
	    }


	
	
}

