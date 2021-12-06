public class Spells {
    public void cast(){
        int number = 25;
        for (int i = 0; i < number; i++) {
            for (int j = 0; j < number; j++) {
                System.out.print(
                        i<j
                                //01 i>j
                                //02 i==j
                                //number-i == j+1
                        ? "*" : "-");
            }
            System.out.println();
        }
    }


}
