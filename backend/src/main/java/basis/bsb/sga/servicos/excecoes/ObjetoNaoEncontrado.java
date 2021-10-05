package basis.bsb.sga.servicos.excecoes;


public class ObjetoNaoEncontrado extends RuntimeException{

    public ObjetoNaoEncontrado() {
        super();
    }

    public ObjetoNaoEncontrado(String s) {
        super(s);
    }
}
