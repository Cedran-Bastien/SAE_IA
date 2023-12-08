public class MainTest {
    public static void main(String[] args) {
        int[][] et = new int[][]{
                new int[]{0,0,0},
                new int[]{0,1,0},
                new int[]{1,0,0},
                new int[]{1,1,1}
        };
        MLP mlp = new MLP(new int[]{2,16,8,4,2},0.001,new Sigmoide());
        for (int i = 0; i<10;i++){
            for (int j =0;j<et.length;j++){
                double[] input = new double[]{et[j][0],et[j][1]};
                double[] output = new double[]{et[j][2]};
                mlp.backPropagate(input,output);
            }
        }
        for (int i = 0; i< et.length;i++){
            double[] input = new double[]{et[i][0],et[i][1]};
            double[] output = mlp.execute(input);
            System.out.println("Fonction ET, input: "+input[0]+" "+input[1]+"\n, output: "+Math.max(output[0],output[1]);
        }
    }
}
