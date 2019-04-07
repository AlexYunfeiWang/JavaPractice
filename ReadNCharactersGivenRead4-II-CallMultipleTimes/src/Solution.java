public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return    The number of actual characters read
     */

    int bufferPointer = 0;
    int len = 0;
    char[] buffer = new char[4];
    public int read(char[] buf, int n) {
        int i = 0;
        while(i < n) {
            if (bufferPointer == len) {
                bufferPointer = 0;
                len = read4(buffer);
                if (len == 0) {
                    break;
                }
            }
            /*
            when bufferPointer is less than len, keep increasing bufferPointer
            and consumes what's in the buffer[] array, until bufferPointer == len,
            that means it has consumed all of the buffer[] array, time to call read4
            to read another chunk of data

            keep repeating this until nothing can be read from read4
            */
            buf[i++] = buffer[bufferPointer++];
        }
        return i;
    }
}