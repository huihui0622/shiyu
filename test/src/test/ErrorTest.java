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

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ErrorTest {

	public static void main(String[] args) throws Exception {
		String path = "E:\\����\\baoming.txt";
		List<String> scanListPath = readFile02(path);

		ArrayList<Object> returnList = new ArrayList<>();
		ArrayList<Double> returnListint = new ArrayList<>();
		ArrayList<String> subList = new ArrayList<>();

		for (String hang : scanListPath) {
			// System.out.println(hang);
			// ��ȡ�ȼ�
			int indexOf = hang.indexOf("��");
			int jibieindex = indexOf + 3;
			String jibie = hang.substring(indexOf + 1, jibieindex + 1);
			// System.out.println("����"+jibie);

			// ��ȡ�ǳ�
			int indexOfname = hang.indexOf("��");
			String substringname = hang.substring(indexOfname + 1);
			// System.out.println("�ǳƣ�"+substringname);

			subList.add("��"+jibie+"��"+substringname);
		}
		
		ArrayList<String> sort2 = sort2(subList);
		for (String string : sort2) {
			System.out.println(string);
		}
		
		
		maintest(sort2);

		// �������
		/*Set<Entry<Double, String>> entrySet = treeMap.entrySet();
		for (Entry<Double, String> entry : entrySet) {
			Double key = entry.getKey();
			String value = entry.getValue();
			System.out.println("�����ģ�" + "����" + "{" + key + "}" + "�ǳ�:" + value);
		}*/
	}

	/**
	 * ��ȡһ���ı� һ��һ�ж�ȡ
	 *
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static List<String> readFile02(String path) throws IOException {
		// ʹ��һ���ַ����������洢�ı��е�·�� ��Ҳ����String []����
		List<String> list = new ArrayList<String>();
		FileInputStream fis = new FileInputStream(path);
		// ��ֹ·������ ���utf-8 ���� ��GBK eclipse�ﴴ����txt ��UTF-8���ڵ������Լ�������txt ��GBK
		InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		String line = "";
		while ((line = br.readLine()) != null) {
			// ��� t x t�ļ����·�� ������---�ַ��� �����Ƕ���������ݽ���һ��ɸѡ
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
	
	
	
	    public static void maintest(ArrayList<String> list) throws Exception {
	    	
	    	int changdishu = list.size()/6;
	    	ArrayList<Object[]> arrayList = new ArrayList<>();
	    	//��ʼ������
	    	Object[] arrinit=new Object[changdishu];
	    	for (int i = 1; i <= changdishu; i++) {
	    		//changdishu[i] = i+"�ų���";
			}
	    	
	    	//һ������6���� ����6��
	    	for (int i = 1; i <= 6; i++) {
	    		
	    		Object[] arr=new Object[changdishu];
	    		int arrindex = 0 ;
	    		for (int j = 0; j < list.size(); j++) {
					if(j/i==0){
						arr[arrindex] = list.get(j);
						arrindex++;
					}
				}
	    		arrayList.add(arr);
			}
	    	
	    	

	        // Create blank workbook
	       XSSFWorkbook workbook = new XSSFWorkbook();

	        // Create a blank sheet
	        XSSFSheet spreadsheet = workbook.createSheet(" Employee Info ");

	        // Create row object
	        XSSFRow row;

	        // This data needs to be written (Object[])
	        Map<String, Object[]> empinfo = new TreeMap<String, Object[]>();
	        empinfo.put("1", new Object[] { "���", "����", "����" });

	        empinfo.put("2", new Object[] { "10010", "����", "��������" });

	        empinfo.put("3", new Object[] { "10011", "������", "΢������" });

	        empinfo.put("4", new Object[] { "10012", "��С��", "��������" });

	        empinfo.put("5", new Object[] { "10013", "����", "NBA��Ա" });

	        empinfo.put("6", new Object[] { "10014", "��˫˫", "����˶�Ա" });

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
	            }
	        }
	        // Write the workbook in file system
	        FileOutputStream out = new FileOutputStream(new File("E:\\����\\Writesheet.xlsx"));

	        workbook.write(out);
	        out.close();
	        System.out.println("Writesheet.xlsx written successfully");
	    }


	
	
}

