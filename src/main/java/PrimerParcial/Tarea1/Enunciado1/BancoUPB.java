package PrimerParcial.Tarea1.Enunciado1;

public class BancoUPB {
    private AfpService afpService;
    private  AsfiService asfiService;
    private SegipService segipService;

    public BancoUPB(){
        afpService = new AfpService();
        asfiService = new AsfiService();
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
