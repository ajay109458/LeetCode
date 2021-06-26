package solid;

public class SingleResponsibiltyDemo {

    /**
     * This class is has multiple reasons to change
     */
    private static class Task {
        public void downloadFile(String filePath) {

        }

        public void parseFile(String filePath) {

        }

        public void persistData(String data) {

        }

    }

}
