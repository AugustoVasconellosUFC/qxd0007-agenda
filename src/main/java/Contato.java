import java.util.ArrayList;

public class Contato {

    private final String name;

    private final ArrayList<Fone> fones = new ArrayList<>();

    public Contato(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getQuantidadeFones(){
        return fones.size();
    }

    public ArrayList<Fone> getFones() {
        return fones;
    }

    public boolean adicionarFone(Fone fone){
        if(Fone.validarNumero(fone.getNumero())) {
            fones.add(fone);
            return true;
        }
        else
            return false;
    }

    public boolean removerFone(int index){
        if(index < 0 || index > fones.size())
            return false;
        else {
            fones.remove(index);
            return true;
        }
    }
    
}
