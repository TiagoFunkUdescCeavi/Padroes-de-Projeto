package loja;

public class Sistema {

    public static void main(String[] args) {
//        Venda v = new Venda( new ValorPorUnidade() );
        Venda v = new Venda( new ValorPorTotal() );
        v.add(new ItemVenda(10, 100));
        v.add(new ItemVenda(1.2, 500));
        v.add(new ItemVenda(30, 300));

        System.out.println(v.pagar());

    }

}
