public class SolveTheEquation {
    public String solveEquation(String equation) {
        if (equation == null || equation.length() == 0) {
            return "0";
        }
        String[] partition = equation.split("\\=");
        int[] left = calculateExpression(partition[0]);
        int[] right = calculateExpression(partition[1]);

        int xSize = left[0] - right[0];
        int constant = right[1] - left[1];

        if (xSize == 0 && constant == 0) {
            return "Infinite solutions";
        }
        if (xSize == 0) {
            return "No solution";
        }

        return "x=" + constant/xSize;
    }

    private int[] calculateExpression(String s) {
        String[] tokens = s.split("(?=[-+])");
        int xSize = 0;
        int constant = 0;

        for (String token : tokens) {
            System.out.println(token);
            if (token.equals("+x") || token.equals("x"))
                xSize += 1;
            else if (token.equals("-x"))
                xSize -= 1;
            else if (token.contains("x"))
                xSize += Integer.parseInt(token.substring(0, token.indexOf("x")));
            else
                constant += Integer.parseInt(token);
        }
        int[] result = new int[2];
        result[0] = xSize;
        result[1] = constant;
        return result;
    }
}
