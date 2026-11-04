public class Connection implements AutoCloseable{

    public void process(){
       throw new IllegalArgumentException("Исключение из process класса Connection");
    }
    @Override
    public void close() throws Exception {
        System.out.println("Close() из класса Connection");
    }
}
