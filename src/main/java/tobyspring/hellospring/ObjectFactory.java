package tobyspring.hellospring;

public class ObjectFactory {
    public PaymentService paymentService() {
        return new PaymentService(exRateProvider());
    }

    /** new 하는것도 분리하여 2개 메서드로 구성 -> 관심사 분리, 메서드가 명확해짐 */
    public ExRateProvider exRateProvider(){
        return new WebApiExRatePaymentProvider();
    }
}
