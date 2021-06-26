package companies;

public class ThreadProblems {


    int counter  = 1 ;
    static int N;

    public void printOddNumber() {
        synchronized (this) {
            while (counter < N) {
                while(counter % 2 == 0) {
                    try {
                        wait();
                    } catch (InterruptedException exception) {
                        exception.printStackTrace();
                    }
                }

                System.out.println(counter);
                counter++;
                notify();
            }
        }
    }

    public void printEvenNumber() {

        synchronized (this) {
            while(counter < N) {
                while (counter % 2 != 0) {
                    try {
                        wait();
                    } catch (InterruptedException exception) {
                        exception.printStackTrace();
                    }
                }
            }

            System.out.println(counter);
            counter++;
            notify();
        }

    }

    public static void main(String[] args) {
        ThreadProblems tp = new ThreadProblems();

        Thread t1 = new Thread(() -> {
            tp.printEvenNumber();
        });

        Thread t2 = new Thread(() -> {
            tp.printOddNumber();
        });

        t1.start();
        t2.start();
    }

}
