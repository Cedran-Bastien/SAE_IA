public class OutestMain {
    public static void main(String[] args) {
        int[][] ou = new int[][]{
                new int[]{0,0,0},
                new int[]{0,1,1},
                new int[]{1,0,1},
                new int[]{1,1,1}
        };
        MLP mlp = new MLP(new int[]{2,16,8,4,1},0.001,new TanH());
        double error = 1;
        while (error > 0.1){
            for (int j =0;j<ou.length;j++){
                double[] input = new double[]{ou[j][0],ou[j][1]};
                double[] output = new double[]{ou[j][2]};
                error = mlp.backPropagate(input,output);
                System.out.printf("loss: %f\n",error);
            }
        }
        for (int i = 0; i< ou.length;i++){
            double[] input = new double[]{ou[i][0],ou[i][1]};
            double[] output = mlp.execute(input);
            boolean res = output[0] > 0.5;
            System.out.println("Fonction OU, input: "+input[0]+" "+input[1]+", output: "+res);
        }
    }
}
