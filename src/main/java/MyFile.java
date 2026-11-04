public class MyFile implements AutoCloseable{

    public void process(){
        System.out.println("Метод process из File");
    }
    @Override
    public void close() throws Exception {
        throw new IllegalArgumentException("Исключение из close() класса File");
    }
}
