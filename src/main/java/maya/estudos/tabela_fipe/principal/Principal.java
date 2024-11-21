package maya.estudos.tabela_fipe.principal;

import maya.estudos.tabela_fipe.model.Dados;
import maya.estudos.tabela_fipe.service.ConsumoApi;
import maya.estudos.tabela_fipe.service.ConverteDados;

import java.util.Comparator;
import java.util.Scanner;

public class Principal {

    private final ConsumoApi consumo = new ConsumoApi();
    private final ConverteDados conversor = new ConverteDados();
    private final Scanner sc = new Scanner(System.in);

    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";

    public void exibeMenu() {
        var menu = """
                *** OPÇÕES ***
                Carro
                Moto
                Caminhão
                
                Digite uma das opções para consulta:
                
                """;

        System.out.println(menu);
        var opcao = sc.nextLine();
        String endereco;

        if (opcao.toLowerCase().contains("carr")){
            endereco = URL_BASE + "carros/marcas";
        } else if (opcao.toLowerCase().contains("mot")) {
            endereco = URL_BASE + "motos/marcas";
        } else {
            endereco = URL_BASE + "caminhoes/marcas";
        }

        var json = consumo.obterDados(endereco);
        System.out.println(json);

        var marcas = conversor.obterLista(json, Dados.class);
        marcas.stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);
    }

}
