
public class Chapter1 {

	public static void exercise1_4(char[] input, int length){
		int spaceCount = 0;
		for(int ii = 0; ii < length; ii++){
			if(input[ii] == ' ')
				spaceCount++;
		}
		if(spaceCount == 0){
			return;
		}
		
		int newLength = length + 2*spaceCount;
		int index = newLength-1;
		for(int ii = length-1; ii >= 0; ii--){
			if(input[ii] != ' '){
				input[index--] = input[ii];
			}else{
				input[index--] = '0';
				input[index--] = '2';
				input[index--] = '%';
			}
		}
		System.out.println(input);
	}
	
	public static void exercise1_5a(String str){
		StringBuilder sb = new StringBuilder();
		char last = str.charAt(0);
		int count = 1;
		for(int ii = 1; ii < str.length(); ii++){
			if(str.charAt(ii) == last){
				count++;
			}else{
				sb.append(last + "" + count);
				last = str.charAt(ii);
				count = 1;
			}
		}
		sb.append(last + "" + count);
		
		System.out.println(sb.toString());
	}
}
