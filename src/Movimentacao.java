import java.time.LocalDate;

public class Movimentacao {
    private LocalDate data;
    private int quantidade;

    public Movimentacao(LocalDate data, int quantidade) {
        this.data = data;
        this.quantidade = quantidade;
    }

    public LocalDate getData() {
        return data;
    }

    public int getQuantidade() {
        return quantidade;
    }
}