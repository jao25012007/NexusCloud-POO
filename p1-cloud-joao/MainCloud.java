public class MainCloud {

    public static void main(String[] args) {

        CloudManager manager = new CloudManager();

        Cliente cliente = new Cliente(
                "111.222.333-44",
                "Wayne Enterprises"
        );

        Servidor servidor = new Servidor(
                "192.168.0.1",
                16.0,
                5.50
        );

        manager.registrarCliente(cliente);
        manager.adicionarServidor(cliente, servidor);

        Container container1 = new Container("C-01", 10.0);
        Container container2 = new Container("C-02", 8.0);

        /*
         * TESTE UNCHECKED:
         * Descomente a linha abaixo para testar a ServidorOfflineException.
         * Depois, comente novamente para o programa continuar normalmente.
         */

        // servidor.alocarContainer(container1);

        servidor.ligar();

        try {
            servidor.alocarContainer(container1);
            System.out.println("Container 1 alocado com sucesso.");
        } catch (CapacidadeExcedidaException e) {
            System.out.println(e.getMessage());
        }

        try {
            servidor.alocarContainer(container2);
        } catch (CapacidadeExcedidaException e) {
            System.out.println(e.getMessage());
        }

        double fatura = manager.calcularFaturaAtiva(cliente);

        System.out.printf("Fatura ativa: R$ %.2f%n", fatura);
    }
}
