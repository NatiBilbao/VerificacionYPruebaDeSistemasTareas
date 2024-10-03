package PrimerParcial.Tarea1.Enunciado1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class BancoTest {
    AfpService afpService = Mockito.mock(AfpService.class);
    SegipService segipService = Mockito.mock(SegipService.class);
    AsfiService asfiService = Mockito.mock(AsfiService.class);

    @Test
    public void verifyTheUserIsAble(){

        Mockito.when(segipService.isRealPerson(888999)).thenReturn(true);
        Mockito.when(asfiService.isAbleToGetCredit(888999)).thenReturn(true);
        Mockito.when(afpService.getAmount(888999)).thenReturn(1000);

        BancoUPB bancoUPB = new BancoUPB();
        bancoUPB.setAsfiService(asfiService);
        bancoUPB.setAfpService(afpService);
        bancoUPB.setSegipService(segipService);
        Assertions.assertEquals("se le puede realizar el prestamo: 3000",
                bancoUPB.getAmountMoney(888999,3000),
                "ERROR el prestamo es incorrecto");

        Mockito.verify(segipService).isRealPerson(888999);
        Mockito.verify(asfiService).isAbleToGetCredit(888999);
        Mockito.verify(afpService).getAmount(888999);
    }

    @Test
    public void verifyTheUserIsNotAbleToRequest(){

        Mockito.when(segipService.isRealPerson(8889998)).thenReturn(false);

        BancoUPB bancoUPB = new BancoUPB();
        bancoUPB.setAsfiService(asfiService);
        bancoUPB.setAfpService(afpService);
        bancoUPB.setSegipService(segipService);
        Assertions.assertEquals("debe revisar su carnet de identidad",
                bancoUPB.getAmountMoney(8889998,3000),
                "ERROR el prestamo es incorrecto");

    }

    @Test
    public void verifyTheUserIsNotAbleAsfi(){

        Mockito.when(segipService.isRealPerson(888999)).thenReturn(true);
        Mockito.when(asfiService.isAbleToGetCredit(888999)).thenReturn(false);
        Mockito.when(afpService.getAmount(888999)).thenReturn(1000);

        BancoUPB bancoUPB = new BancoUPB();
        bancoUPB.setAsfiService(asfiService);
        bancoUPB.setAfpService(afpService);
        bancoUPB.setSegipService(segipService);
        Assertions.assertEquals("usted no esta habilitado para prestamos",
                bancoUPB.getAmountMoney(888999,3000),
                "ERROR el prestamo es incorrecto");

    }
}
