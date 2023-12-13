package Grafos;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MatrizAdjacente {
	private int[][] G;
	private int numVertices;
	private boolean ponderado;
	private boolean direcionado;

	public MatrizAdjacente(int numVertices, boolean ponderado, boolean direcionado) {
		this.numVertices = numVertices;
		this.direcionado = direcionado;
		this.ponderado = ponderado;
		G = new int[numVertices][numVertices];
	}

	public void inserirAresta(int vertice1, int vertice2) {
		if (!ponderado) {
			G[vertice1][vertice2] = 1;
			if (!direcionado) {
				G[vertice2][vertice1] = 1;
			}
		} else {
			System.out.println("Grafo ponderado. Necessita de peso da aresta");
		}
	}

	public void inserirAresta(int vertice1, int vertice2, int peso) {
		if (ponderado) {
			G[vertice1][vertice2] = peso;
			if (!direcionado) {
				G[vertice2][vertice1] = peso;
			}
		} else {
			System.out.println("Grafo não ponderado. Arestas nao possuem pesos");
		}
	}

	public void removerAresta(int vertice1, int vertice2) {
		G[vertice1][vertice2] = 0;
		if (!direcionado) {
			G[vertice2][vertice1] = 0;
		}
	}
	
	public void mostrarMatrizAdjacentes() {
		for (int i = 0; i < numVertices; i++) {
			for (int j= 0; j < numVertices; j++) {
				System.out.print(G[i][j]+" ");	
			}
				System.out.println();
		}
	}

	public boolean verificaAdjascencia(int vertice1, int vertice2) {
		return G[vertice1][vertice2] != 0;
		 
	}
	
	public void removerVertice(int vertice) {         // Questão 1a
	    // Remover arestas conectadas ao vértice
	    for (int i = 0; i < numVertices; i++) {
	        G[vertice][i] = 0;
	        if (!direcionado) {
	            G[i][vertice] = 0;
	        }
	    }
	}
	
	public boolean isConexo() {                              // Questão 1b
	    boolean[] visitado = new boolean[numVertices];
	    dfs(0, visitado);
	    for (int i = 0; i < numVertices; i++) {
	        if (!visitado[i]) {
	            return false;
	        }
	    }
	    return true;
	}

	private void dfs(int vertice, boolean[] visitado) {
	    visitado[vertice] = true;
	    for (int i = 0; i < numVertices; i++) {
	        if (G[vertice][i] != 0 && !visitado[i]) {
	            dfs(i, visitado);
	        }
	    }
	}

	
	public boolean isGrafoCompleto() {                    // Questão 1c
		   int grauMaximo = numVertices - 1;
		   for (int i = 0; i < numVertices; i++) {
		       int grau = 0;
		       for (int j = 0; j < numVertices; j++) {
		           if (G[i][j] != 0) {
		               grau++;
		           }
		       }
		       if (grau != grauMaximo) {
		           return false;
		       }
		   }
		   return true;
		}
	
	public int[] dijkstra(int origem) {                         // Questão 1d
		   int[] distancias = new int[numVertices];
		   boolean[] visitados = new boolean[numVertices];

		   // Inicializa todas as distâncias como infinito e todos os vértices como não visitados
		   for (int i = 0; i < numVertices; i++) {
		       distancias[i] = Integer.MAX_VALUE;
		       visitados[i] = false;
		   }

		   // A distância do vértice de origem para si mesmo é 0
		   distancias[origem] = 0;

		   // Enquanto houver vértices não visitados
		   while (true) {
		       // Encontra o vértice não visitado com a menor distância
		       int minDistancia = Integer.MAX_VALUE;
		       int verticeMin = -1;
		       for (int i = 0; i < numVertices; i++) {
		           if (!visitados[i] && distancias[i] < minDistancia) {
		               minDistancia = distancias[i];
		               verticeMin = i;
		           }
		       }

		       // Se todos os vértices foram visitados, termina o algoritmo
		       if (verticeMin == -1) {
		           break;
		       }

		       // Marca o vértice mínimo como visitado
		       visitados[verticeMin] = true;

		       // Atualiza as distâncias dos vizinhos do vértice mínimo
		       for (int i = 0; i < numVertices; i++) {
		           if (G[verticeMin][i] != 0 && distancias[verticeMin] + G[verticeMin][i] < distancias[i]) {
		               distancias[i] = distancias[verticeMin] + G[verticeMin][i];
		           }
		       }
		   }

		   return distancias;
		}

	
	public String verificaEuleriano() {                 // Questão 1e
	    int grauImpar = 0;
	    for (int i = 0; i < numVertices; i++) {
	        int grau = 0;
	        for (int j = 0; j < numVertices; j++) {
	            if (G[i][j] != 0) {
	                grau++;
	            }
	        }
	        if (grau % 2 != 0) {
	            grauImpar++;
	        }
	    }
	    if (grauImpar == 0) {
	        return "Euleriano";
	    } else if (grauImpar == 2) {
	        return "Semieuleriano";
	    } else {
	        return "Não euleriano";
	    }
	}


	public String verificaHamiltoniano() {             // Questão 1f
	    List<Integer> vertices = IntStream.range(0, numVertices).boxed().collect(Collectors.toList());
	    return verificaPermutacoes(vertices, new boolean[numVertices], 0) ? "Hamiltoniano" : "Não Hamiltoniano";
	}

	private boolean verificaPermutacoes(List<Integer> vertices, boolean[] visitado, int idx) {
	    if (idx == numVertices) {
	        return G[vertices.get(idx - 1)][vertices.get(0)] == 1;
	    }
	    for (int i = idx; i < numVertices; i++) {
	        if (idx == 0 || (G[vertices.get(idx - 1)][vertices.get(i)] == 1 && !visitado[vertices.get(i)])) {
	            Collections.swap(vertices, idx, i);
	            visitado[vertices.get(idx)] = true;
	            if (verificaPermutacoes(vertices, visitado, idx + 1)) {
	                return true;
	            }
	            visitado[vertices.get(idx)] = false;
	            Collections.swap(vertices, idx, i);
	        }
	    }
	    return false;
	}

	
	public String verificaSemiHamiltoniano() {
	    for (int i = 0; i < numVertices; i++) {
	        for (int j = 0; j < numVertices; j++) {
	            if (G[i][j] != 0) {
	                G[i][j] = 0;
	                String resultado = verificaHamiltoniano();
	                G[i][j] = 1;
	                if (resultado.equals("Hamiltoniano")) {
	                    return "SemiHamiltoniano";
	                }
	            }
	        }
	    }
	    return "Não Hamiltoniano";
	}

	

	
}