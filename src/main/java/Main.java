public class Main {

    public static void execute() throws Exception{

        try(Session session = new Session();
            MyFile myFile = new MyFile();
            Connection connection = new Connection()){

            session.process();
            connection.process();
            myFile.process();
        }
    }
    public static void main(String[] args) throws Exception{
        try {
            execute();
        }catch (Exception e){
            System.err.printf("Основное исключение: %s \n", e.getMessage());
            for (Throwable suppressed : e.getSuppressed()) {
                System.err.printf("Suppressed: %s%n", suppressed.getMessage());
            }
        }
    }

    /*
        Лог будет такой:

        Метод process из класса Session
        Close() из класса Connection
        Close() из класса Session
        Основное исключение: Исключение из process класса Connection
        Suppressed: Исключение из close() класса MyFile

        Сначала выполняются методы каждого класса: Session, Connection, MyFile
        Однако во время выполнения метода process класса Connection выбрасывается исключение,
        поэтому следующий за ним вызов метода process класса File, не выполняется.

        Исключение, которые выбрасывается на этом этапе, является основным (в отличие от стандартного блока try-catch-finally)

        Далее происходит закрытие ресурсов в обратном порядке, т.е.первым закрывается Connection, а последним Session
        На этом этапе при вызове метода close() класса MyFile возникает исключение, которое добавляется в Suppressed Exception
        В конце закрывается Session.

        Suppressed exception - это исключение, возникшее в close() (или finally), когда основное исключение уже брошено в try.
        Без механизма suppressed оно бы перебило основное, а основное исключение было бы потеряно.
        В try-with-resources JVM сохраняет основное исключение, а исключения из close() добавляет к нему через addSuppressed().
        В классическом try-finally исключение из finally полностью заменяет исходное,
        поэтому в этом случае нужно самостоятельно добавлять Suppressed exception
     */
}
