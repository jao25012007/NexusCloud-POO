import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Servidor {

    private String ip;
    private double ramTotal;
    private double precoPorHora;
    private boolean statusLigado;
    private List<Container> containers = new ArrayList<>();

    public Servidor(String ip, double ramTotal, double precoPorHora) {
        this.ip = ip;
        this.ramTotal = ramTotal;
        this.precoPorHora = precoPorHora;
        this.statusLigado = false;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public double getRamTotal() {
        return ramTotal;
    }

    public void setRamTotal(double ramTotal) {
        this.ramTotal = ramTotal;
    }

    public double getPrecoPorHora() {
        return precoPorHora;
    }

    public void setPrecoPorHora(double precoPorHora) {
        this.precoPorHora = precoPorHora;
    }

    public boolean isStatusLigado() {
        return statusLigado;
    }

    public List<Container> getContainers() {
        return containers;
    }

    public void ligar() {
        statusLigado = true;
    }

    public void desligar() {
        statusLigado = false;
    }

    public double calcularRamEmUso() {
        return containers
                .stream()
                .mapToDouble(Container::getRamNecessaria)
                .sum();
    }

    public void alocarContainer(Container c) throws CapacidadeExcedidaException {

        if (!statusLigado) {
            throw new ServidorOfflineException(
                    "Erro: Não é possível alocar containers em um servidor desligado."
            );
        }

        if (calcularRamEmUso() + c.getRamNecessaria() > ramTotal) {
            throw new CapacidadeExcedidaException(
                    "Falha Crítica: Memória RAM insuficiente no servidor " + ip + "."
            );
        }

        containers.add(c);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Servidor)) return false;

        Servidor servidor = (Servidor) o;
        return Objects.equals(ip, servidor.ip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ip);
    }
}
