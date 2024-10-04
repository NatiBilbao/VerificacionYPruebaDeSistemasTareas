package PrimerParcial.Tarea2.Enunciado1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

public class BancoTest {
    PrimerParcial.Tarea1.Enunciado1.AfpService afpService = Mockito.mock(String.valueOf(AfpService.class));
    PrimerParcial.Tarea1.Enunciado1.SegipService segipService = Mockito.mock(String.valueOf(SegipService.class));
    PrimerParcial.Tarea1.Enunciado1.AsfiService asfiService = Mockito.mock(String.valueOf(AsfiService.class));

    @ParameterizedTest
    @CsvSource({
            "888999, true, true, 1000, 3000, 'se le puede realizar el prestamo: 3000'",
            "8889998, false, false, 0, 3000, 'debe revisar su carnet de identidad'",
            "888999, true, false, 1000, 3000, 'usted no esta habilitado para prestamos'"
    })
    public void verifyUserIsAbleToGetLoan(int userId, boolean isRealPerson, boolean isAbleToGetCredit, int afpAmount, int requestedLoan, String expectedResult) {

        Mockito.when(segipService.isRealPerson(userId)).thenReturn(isRealPerson);
        if (isRealPerson) {
            Mockito.when(asfiService.isAbleToGetCredit(userId)).thenReturn(isAbleToGetCredit);
            Mockito.when(afpService.getAmount(userId)).thenReturn(afpAmount);
        }

        BancoUPB bancoUPB = new BancoUPB();
        bancoUPB.setAsfiService(asfiService);
        bancoUPB.setAfpService(afpService);
        bancoUPB.setSegipService(segipService);

        String actualResult = bancoUPB.getAmountMoney(userId, requestedLoan);
        Assertions.assertEquals(expectedResult, actualResult, "ERROR el prestamo es incorrecto");

        Mockito.verify(segipService).isRealPerson(userId);
        if (isRealPerson) {
            Mockito.verify(asfiService).isAbleToGetCredit(userId);
            Mockito.verify(afpService).getAmount(userId);
        }
    }
}
