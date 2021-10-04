package basis.bsb.sga.servicos.excecoes;

public class ValidadorExcecoes extends RuntimeException{

    public ValidadorExcecoes() {
        super();
    }
    public ValidadorExcecoes(String s) {
        super(s);
    }
}
