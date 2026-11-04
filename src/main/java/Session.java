public class Session implements AutoCloseable{

    public void process(){
        System.out.println("Метод process из класса Session");
    }
    @Override
    public void close() throws Exception {
        System.out.println("Close() из класса Session");
    }
}
