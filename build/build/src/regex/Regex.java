package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

	public class Regex {

		Pattern pattern;
		Matcher m;
		String [] sp;
		public Regex(String s) {
			//this.pattern= Pattern.compile("((return)|(if)|(while)|(var)|(connect)|(disconnect)|(openDataServer)|(bind)|(simX)|(simY)|(simZ)|(;)|"
			//		+ "(^\t)|[A-Z]|[a-z]|(\\d*\\.\\d+)|(\\d+)"
			//		+ "|([\\=\\+\\-\\*/\\(\\)\\>\\<]))");
		//	this.pattern=Pattern.compile("((?<=[a-zA-Z0-9={}])\\s(?=[a-zA-Z0-9={}(]))|[\\n\\r]+|((?<=[{}])|(?=[{}]))|((?<=[=])|(?=[=]))");
		//	this.m = this.pattern.matcher(s);
			String line1 = s.trim().replaceAll(" +", " ");
			this.sp =line1.split("((?<=[a-zA-Z0-9={}])\\s(?=[a-zA-Z0-9={}(]))|[\\n\\r]+|((?<=[{}])|(?=[{}]))|((?<=[=])|(?=[=]))");
			
		}
		public String[] getSp() {
			return this.sp;
		}
		public StringBuilder expAsStr(StringBuilder b) {
			
			//while(m.find()) {
			//	b.append(m.group()+" ");
			//}
			return b;
		}
}
