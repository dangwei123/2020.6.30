给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。

'.' 匹配任意单个字符
'*' 匹配零个或多个前面的那一个元素
所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/regular-expression-matching
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class Solution {
    public boolean isMatch(String s, String p) {
        int m=s.length();
        int n=p.length();
        boolean[][] dp=new boolean[m+1][n+1];
        dp[0][0]=true;
        for(int i=2;i<=n;i++){
            dp[0][i]=dp[0][i-2]&&p.charAt(i-1)=='*';
        }
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                if(p.charAt(j-1)=='*'){
                    dp[i][j]=(isMatch(s,i-1,p,j-2)&&dp[i-1][j])||dp[i][j-2];
                }else{
                    dp[i][j]=dp[i-1][j-1]&&isMatch(s,i-1,p,j-1);
                }
            }
        }
        return dp[m][n];
    }
    private boolean isMatch(String s,int i,String p,int j){
        return s.charAt(i)==p.charAt(j)||p.charAt(j)=='.';
    }
}