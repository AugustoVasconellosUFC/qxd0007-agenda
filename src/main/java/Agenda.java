import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Agenda {


    private final TreeMap<String,Contato> contatos = new TreeMap<>();

    public List<Contato> getContatos() { return new ArrayList<>(contatos.values()); }

    public int getQuantidadeDeContatos() { return contatos.size(); }

    public Contato getContato(String name){ return contatos.get(name); }

    public boolean adicionarContato(Contato contato){
        if(!contatos.containsKey(contato.getName()) && contato.getQuantidadeFones() > 0) {
            contatos.put(contato.getName(),contato);
            return true;
        } else {
            for(Fone fone : contato.getFones()) {
                contatos.get(contato.getName()).adicionarFone(fone);
            }
            return false;
        }
    }

    public boolean removerContato(String name){
        if(contatos.containsKey(name)) {
            contatos.remove(name);
            return true;
        } else
            return false;
    }

    public boolean removerFone(String name, int index){
        if(contatos.containsKey(name)) {
            return contatos.get(name).removerFone(index);
        } else
            return false;
    }

    public int getQuantidadeDeFones(Identificador identificador){
        AtomicInteger qtd = new AtomicInteger();
        contatos.forEach((key, value) -> {
            for(Fone fone : value.getFones()) {
                if(fone.getIdentificador() == identificador)
                    qtd.addAndGet(1);
            }
        });
        return qtd.get();
    }

    public int getQuantidadeDeFones(){
        AtomicInteger qtd = new AtomicInteger();
        contatos.forEach((key, value) -> qtd.addAndGet(value.getQuantidadeFones()));
        return qtd.get();
    }

    public ArrayList<Contato> pesquisar(String expressao){
        ArrayList<Contato> list = new ArrayList<>();
        Pattern pattern = Pattern.compile(expressao);
        contatos.forEach((key, value) -> {
            Matcher matcher = pattern.matcher(value.getName());
            if(matcher.find())
                list.add(value);
            else {
                for(Fone fone : value.getFones()) {
                    matcher = pattern.matcher(fone.getNumero());
                    if(matcher.find()) {
                        list.add(value);
                        break;
                    }
                }
            }
        });
        list.sort(Comparator.comparing(Contato::getName));
        return list;
    }

}