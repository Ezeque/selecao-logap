package com.logap.backend.Service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.logap.backend.Models.Fornecedor;
import com.logap.backend.Models.Produto;
import com.logap.backend.Repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

@Service
public class RelatorioService {
    @Autowired
    ProdutoService produtoService;

    /* CRIA RELATÓRIO DO SETOR */
    public byte[] criaRelatorio(){
        List<Produto> produtos = produtoService.recuperaTodosProdutos();
        List<Produto> produtosEmFalta = produtoService.recuperaProdutosFalta();
        Map<String, List<Produto>> fornecedoresEmFalta = infereFornecedoresFalta(produtosEmFalta);

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        try{
            Document documento = new Document(PageSize.A4, 36f, 72f, 20, 20);
            PdfWriter.getInstance(documento, bytes);
            documento.open();

            /* HEADER DO RELATÓRIO */
            Paragraph titulo = new Paragraph( "Relatório de Estoque" + "\n" + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    FontFactory.getFont(FontFactory.HELVETICA, 36));
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);
            documento.add(new Paragraph("\n\n\n\n\n"));


            /* TABELA DE PRODUTOS */
            documento.add(new Paragraph("Todos os Produtos:", FontFactory.getFont(FontFactory.HELVETICA, 20)));
            documento.add(new Paragraph("\n"));
            PdfPTable tabelaProdutos = new PdfPTable(6);
            tabelaProdutos.setWidthPercentage(100);
            tabelaProdutos.addCell("Nome:");
            tabelaProdutos.addCell("Fornecedor:");
            tabelaProdutos.addCell("Categoria:");
            tabelaProdutos.addCell("Valor (R$):");
            tabelaProdutos.addCell("Quantidade:");
            tabelaProdutos.addCell("Localização:");
            if(produtos.size() == 0){
                tabelaProdutos.addCell("Não há produtos cadastrados.");
            }
            else{
                for(Produto produto : produtos){
                    tabelaProdutos.addCell(produto.getName());
                    tabelaProdutos.addCell(produto.getFornecedor().getNome());
                    tabelaProdutos.addCell(produto.getCategoria().getNome());
                    tabelaProdutos.addCell(String.valueOf(produto.getValor()));
                    tabelaProdutos.addCell(String.valueOf(produto.getQuantidade()));
                    tabelaProdutos.addCell(produto.getLocalizacao());
                }
                documento.add(tabelaProdutos);
                documento.add(new Paragraph("\n\n\n\n\n"));
            }

            /* TABELA DE PRODUTOS EM FALTA */

            documento.add(new Paragraph("Produtos em Falta:", FontFactory.getFont(FontFactory.HELVETICA, 20)));
            documento.add(new Paragraph("\n"));
            PdfPTable tabelaProdutosFalta = new PdfPTable(3);
            tabelaProdutosFalta.setWidthPercentage(100);
            tabelaProdutosFalta.addCell("Nome:");
            tabelaProdutosFalta.addCell("Fornecedor:");
            tabelaProdutosFalta.addCell("Categoria:");

            if(produtosEmFalta.size() == 0){
                tabelaProdutos.addCell("Não há produtos em falta.");
            }
            else{
                for(Produto produto : produtosEmFalta){
                    tabelaProdutosFalta.addCell(produto.getName());
                    tabelaProdutosFalta.addCell(produto.getFornecedor().getNome());
                    tabelaProdutosFalta.addCell(produto.getCategoria().getNome());
                }
            }
            documento.add(tabelaProdutosFalta);
            documento.add(new Paragraph("\n\n\n\n\n"));

            /* TABELA DE FORNECEDORES DE PRODUTOS EM FALTA */

            documento.add(new Paragraph("Fornecedores com Produtos em Falta:", FontFactory.getFont(FontFactory.HELVETICA, 20)));
            documento.add(new Paragraph("\n"));
            PdfPTable tabelaFornecedoresFalta = new PdfPTable(2);
            tabelaFornecedoresFalta.setWidthPercentage(100);
            tabelaFornecedoresFalta.addCell("Fornecedor:");
            tabelaFornecedoresFalta.addCell("Produtos:");

            if(fornecedoresEmFalta.size() == 0){
                tabelaProdutos.addCell("Não há produtos em falta.");
            }
            else{
                for(Map.Entry<String, List<Produto>> fornecedor : fornecedoresEmFalta.entrySet()){
                    String produtosFornecedor = "";
                    tabelaFornecedoresFalta.addCell(fornecedor.getKey());
                    for(Produto produto : fornecedor.getValue()){
                        if(produto.getQuantidade() == 0){
                            produtosFornecedor = produtosFornecedor.concat(produto.getName() + "\n\n");
                        }
                    }
                    tabelaFornecedoresFalta.addCell(produtosFornecedor);
                }
            }
            documento.add(tabelaFornecedoresFalta);


            documento.close();
            return bytes.toByteArray();
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    /* RETORNA MAP COM NOME DO FORNECEDOR E LISTA DE PRODUTOS EM FALTA QUE ELE OFERECE */
    private Map<String, List<Produto>> infereFornecedoresFalta(List<Produto> produtosEmFalta){
        Map<String, List<Produto>> fornecedoresFaltaMap = new HashMap<>();
        for(Produto produto : produtosEmFalta){
            if(!(fornecedoresFaltaMap.containsKey(produto.getFornecedor().getNome()))){
                ArrayList<Produto> listaProduto = new ArrayList<Produto>();
                listaProduto.add(produto);
                fornecedoresFaltaMap.put(produto.getFornecedor().getNome(), listaProduto);
            }
            else{

                fornecedoresFaltaMap.get(produto.getFornecedor().getNome()).add(produto);
            }
        }
        return fornecedoresFaltaMap;
    }
}
