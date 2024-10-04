package PrimerParcial.Tarea2.Enunciado1;

import PrimerParcial.Tarea1.Enunciado1.AfpService;
import PrimerParcial.Tarea1.Enunciado1.AsfiService;
import PrimerParcial.Tarea1.Enunciado1.SegipService;

public class BancoUPB {
    private PrimerParcial.Tarea1.Enunciado1.AfpService afpService;
    private PrimerParcial.Tarea1.Enunciado1.AsfiService asfiService;
    private SegipService segipService;

    public BancoUPB(){
        afpService = new PrimerParcial.Tarea1.Enunciado1.AfpService();
        asfiService = new PrimerParcial.Tarea1.Enunciado1.AsfiService();
        segipService = new SegipService();
    }

    public void setAfpService(AfpService afpService) {
        this.afpService = afpService;
    }

    public void setAsfiService(AsfiService asfiService) {
        this.asfiService = asfiService;
    }

    public void setSegipService(SegipService segipService) {
        this.segipService = segipService;
    }

    public String getAmountMoney(int ci, int amount){
        if (segipService.isRealPerson(ci)){
            if (asfiService.isAbleToGetCredit(ci)){
                int basicAmount = afpService.getAmount(ci);
                int total = basicAmount*3;
                return "se le puede realizar el prestamo: "+total;
            }else{
                return "usted no esta habilitado para prestamos";
            }
        } else{
            return "debe revisar su carnet de identidad";
        }
    }
}
