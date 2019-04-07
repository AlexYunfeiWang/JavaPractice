public class LicenseKeyFormatting {
    public String licenseKeyFormatting(String S, int K) {
        StringBuilder sb = new StringBuilder();
        int dashes = 0;

        for (int i = S.length()-1; i >= 0; --i) {
            char c = S.charAt(i);
            if (c == '-') {
                continue;
            }
            sb.append(c);
            if ((sb.length() - dashes)%K == 0) {
                sb.append('-');
                dashes++;
            }
        }
        if (sb.length() > 0 && sb.charAt(sb.length()-1) == '-')
            sb.deleteCharAt(sb.length()-1);
        return sb.reverse().toString().toUpperCase();
    }
}
