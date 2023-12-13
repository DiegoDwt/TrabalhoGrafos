package Grafos;

public class Main {
	
    public static void main(String[] args) {
    	
        // Cria um grafo com 5 vértices, ponderado e direcionado
        MatrizAdjacente grafo = new MatrizAdjacente(5, true, true);

        // Adiciona as arestas conforme a descrição
        grafo.inserirAresta(0, 1, 1);
        grafo.inserirAresta(0, 2, 2);
        grafo.inserirAresta(0, 3, 3);
        grafo.inserirAresta(0, 4, 4);
        grafo.inserirAresta(1, 2, 5);
        grafo.inserirAresta(1, 3, 6);
        grafo.inserirAresta(1, 4, 7);
        grafo.inserirAresta(2, 3, 8);
        grafo.inserirAresta(2, 4, 9);
        grafo.inserirAresta(3, 4, 10);
        
        System.out.println("Inserir: ");
        grafo.mostrarMatrizAdjacentes();
        System.out.println();
       

        // Testa o método removerVertice
        System.out.println("Remover: 1) - a");
        grafo.removerVertice(0);
        grafo.mostrarMatrizAdjacentes();
        System.out.println();
        
        System.out.println();

        // Testa o método isConexo
        System.out.println("É conexo: 1) - b");
        System.out.println(grafo.isConexo());
        System.out.println();

        // Testa o método isGrafoCompleto
        System.out.println("É completo: 1) - c");
        System.out.println(grafo.isGrafoCompleto());
        System.out.println();

        // Testa o método verificaEuleriano
        System.out.println("Algoritmo de Dijkstra: 1) - d");
        MatrizAdjacente grafo2 = new MatrizAdjacente(5, true, true);
        grafo2.inserirAresta(0, 1, 1);
        grafo2.inserirAresta(0, 4, 10);
        grafo2.inserirAresta(0, 3, 3);
        grafo2.inserirAresta(1, 2, 5);
        grafo2.inserirAresta(2, 4, 1);
        grafo2.inserirAresta(3, 2, 2);
        grafo2.inserirAresta(3, 4, 6);
        int[] distancias = grafo2.dijkstra(0);
        for (int i = 0; i < distancias.length; i++) {
            System.out.println("Distância de 0 para " + i + ": " + distancias[i]);
        }
        System.out.println();

        // Testa o método verificaHamiltoniano
        System.out.println("É euleriano/semieuleriano/não euleriano: 1) - e");
        System.out.println(grafo.verificaEuleriano());
        System.out.println();

        // Testa o método verificaSemiHamiltoniano
        System.out.println("É Hamiltoniano/não hamiltoniano: 1) - f");
        System.out.println(grafo.verificaHamiltoniano());
        System.out.println();
  
       
        
        MatrizAdjacente grafo3 = new MatrizAdjacente(5, true, true);
        grafo3.inserirAresta(0, 1, 1);
        grafo3.inserirAresta(0, 4, 10);
        grafo3.inserirAresta(0, 3, 3);
        grafo3.inserirAresta(1, 2, 5);
        grafo3.inserirAresta(2, 4, 1);
        grafo3.inserirAresta(3, 2, 2);
        grafo3.inserirAresta(3, 4, 6);

        int[] distancias2 = grafo3.dijkstra(0);
        System.out.println("2) - a");
        for (int i = 0; i < distancias.length; i++) {
            System.out.println("Distância de 0 para " + i + ": " + distancias2[i]);
        }
    
        System.out.println();
	    MatrizAdjacente grafo4 = new MatrizAdjacente(6, true, true);
	    grafo4.inserirAresta(0, 1, 15);
	    grafo4.inserirAresta(0, 2, 9);
	    grafo4.inserirAresta(1, 3, 2);
	    grafo4.inserirAresta(2, 1, 4);
	    grafo4.inserirAresta(2, 3, 3);
	    grafo4.inserirAresta(3, 4, 6);
	    grafo4.inserirAresta(3, 5, 21);
	    grafo4.inserirAresta(4, 5, 7);
	
	    int[] distancias3 = grafo4.dijkstra(0);
	     System.out.println("2) - b");
	     System.out.println("Distância de 1 para 6: " + distancias3[5]);
	    }
    
    
}
