package maya.estudos.tabela_fipe.service;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
}
