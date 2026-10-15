import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CloudManager {

    private Map<Cliente, Set<Servidor>> bancoDeDados = new HashMap<>();

    public Map<Cliente, Set<Servidor>> getBancoDeDados() {
        return bancoDeDados;
    }

    public void registrarCliente(Cliente cliente) {
        bancoDeDados.putIfAbsent(cliente, new HashSet<>());
    }

    public void adicionarServidor(Cliente cliente, Servidor servidor) {
        bancoDeDados
                .computeIfAbsent(cliente, c -> new HashSet<>())
                .add(servidor);
    }

    public double calcularFaturaAtiva(Cliente c) {
        return bancoDeDados
                .getOrDefault(c, new HashSet<>())
                .stream()
                .filter(Servidor::isStatusLigado)
                .mapToDouble(Servidor::getPrecoPorHora)
                .sum();
    }
}
