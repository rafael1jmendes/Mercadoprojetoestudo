package main;

import model.Product;
import utility.Utilitys;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Mercado {
    private static Scanner input = new Scanner(System.in);
    public static ArrayList<Product> products;

    private static Map<Product, Integer> carrinho;

    public static void main(String[] args) {
        products = new ArrayList<>();
        carrinho = new HashMap<>();

        menu();
    }

    public static void menu() {
        System.out.println("--------------------------------------------------");
        System.out.println("-----------------| Mendes Market |----------------");
        System.out.println("--------------------------------------------------");
        System.out.println("-------**** SELECIONE A OPCAO DESEJADA ****-------");
        System.out.println("--------------------------------------------------");
        System.out.println("1 - CADASTRAR PRODUTO");
        System.out.println("2 - LISTAR PRODUTO");
        System.out.println("3 - COMPRAR PRODUTO");
        System.out.println("4 - CARRINHO");
        System.out.println("5 - SAIR");

        int option = input.nextInt();
        switch (option) {
            case 1:
                cadastrarProdutos();
                break;
                case 2:
                    listarProdutos();
                    break;
                    case 3:
                        comprarProdutos();
                        break;
                        case 4:
                            verCarrinho();
                            break;
                            case 5:
                                System.out.println("OBRIGADO PELA PREFERENCIA");
                                System.exit(0);
            default:
                System.out.println("OPCAO INVALIDA");
                menu();
                break;
        }

    }

    private static void cadastrarProdutos() {
        System.out.println("NOME DO PRODUTO: ");
        String nome = input.next();

        System.out.println("PRECO DO PRODUTO: ");
        Double preco = input.nextDouble();

        Product produto = new Product(nome, preco);
        products.add(produto);

        System.out.println(produto.getName() + " CADASTRADO COM SUCESSO!");
        menu();
    }
    private static void listarProdutos() {
        if (products.size() > 0) {
            System.out.println("LISTA DE PRODUTOS: \n");

            for (Product p : products) {
                System.out.println(p);
            }
            }else {
            System.out.println("NENHUM PRODUTO NA LISTA");

        }
        menu();
    }
    private static void comprarProdutos() {
        if (products.size() > 0) {
            System.out.println("CODIGO DO PRODUTO: \n");

            System.out.println("--------------PRODUTOS DISPONIVEIS-------------");
            for (Product p : products) {
                System.out.println(p + "\n");
            }
            int id = Integer.parseInt(input.next());
            boolean isPresent = false;

            for (Product p : products) {
                if (p.getId() == id) {
                    int qtd = 0;
                    try {
                       qtd = carrinho.get(p);
                       // checar se o produto ja esta no carrinho. acrescenta qtd
                       carrinho.put(p, qtd + 1);
                    } catch (NullPointerException e) {
                        // se o produto for o primeiro do carrinho
                        carrinho.put(p, 1);
                    }

                    System.out.println(p.getName() + "ADICIONADO AO CARRINHO COM SUCESSO!");
                    isPresent = true;

                    if (isPresent){
                        System.out.println("DESEJAR ADICIONAR OUTRO PRODUTO ?");
                        System.out.println("DIGITE 1 PARA SIM, OU 0 PARA FINALIZAR A COMPRA \n");
                        int option = Integer.parseInt(input.next());

                        if (option == 1) {
                            comprarProdutos();
                        } else {
                            finalizarCompra();
                        }
                    }
                } else {
                    System.out.println("NENHUM PRODUTO NA LISTA");
                    menu();
                }
            }
        } else {
            System.out.println("PRODUTO NAO CADASTRADO");
            menu();
        }
    }

    private static void verCarrinho() {
        System.out.println("---PRODUTOS NO SEU CARRINHO---");
        if (carrinho.size() > 0) {
            for (Product p : carrinho.keySet()) {
                System.out.println("Produto: " + p.getName() + ", Quantidade: " + carrinho.get(p));
            }
        } else {
            System.out.println("NENHUM PRODUTO NA CARRINHO");
        }
        menu();
    }

    private static void finalizarCompra() {
        Double valorTotal = 0.0;
        System.out.println("SEUS PRODUTOS!");

        for (Product p : carrinho.keySet()) {
            int qtd = carrinho.get(p);
            valorTotal += p.getPrice() * qtd;
            System.out.println(p);
            System.out.println("QUANTIDADE: " + qtd);
        }
        System.out.println("VALOR TOTAL: " + Utilitys.doubleToString(valorTotal));
        carrinho.clear();
        System.out.println("COMPRA FINALIZADA COM SUCESSO!");
    }
}
