public class XorTestMain {
    public static void main(String[] args) {

        // Emtrainment
        int[][] xor = new int[][]{
                new int[]{-1, -1, -1},
                new int[]{-1, 1, 1},
                new int[]{1, -1, 1},
                new int[]{1, 1, -1}
        };
        MLP mlp = new MLP(new int[]{2, 2, 1}, 0.01, new TanH());
        double error = 1;
        while (error > 0.1){
            for (int j = 0; j < xor.length; j++) {
                double[] input = new double[]{xor[j][0], xor[j][1]};
                double[] output = new double[]{xor[j][2]};
                error = mlp.backPropagate(input, output);
                System.out.printf("loss: %f\n", error);
            }
        }

        // Test
        for (int i = 0; i < xor.length; i++) {
            double[] input = new double[]{xor[i][0], xor[i][1]};
            double[] output = mlp.execute(input);
            boolean res = output[0] > 0;
            System.out.println("Fonction XOR, input: " + input[0] + " " + input[1] + ", output: " + res);
        }
    }
}
