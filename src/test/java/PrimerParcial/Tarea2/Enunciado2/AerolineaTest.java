package PrimerParcial.Tarea2.Enunciado2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

public class AerolineaTest {
    private Aereolinea aereolinea;

    @BeforeEach
    public void setup() {
        aereolinea = new Aereolinea();
    }

    @ParameterizedTest
    @CsvSource({
            "Santa Cruz,10,10,10,2023,no existen suficientes pasajes para Santa Cruz",
            "La Paz,2,29,5,2023,el dia Lunes 29 Mayo 2023 existen 2 pasajes para La Paz",
            "Cochabamba,5,6,2,2023,el dia Martes 6 Febrero 2023 existen 5 pasajes para Cochabamba",
            "Beni,15,20,7,2023,el dia Miercoles 20 Julio 2023 existen 15 pasajes para Beni",
            "Pando,21,21,4,2023,el dia Jueves 21 Abril 2023 existen 21 pasajes para Pando",
            "Tarija,21,15,4,2023,el dia Viernes 15 Abril 2023 existen 21 pasajes para Tarija",
            "Potosi,2,2,2,2023,el dia Sabado 2 Febrero 2023 existen 2 pasajes para Potosi",
            "Sucre,3,3,3,2023,el dia Domingo 3 Marzo 2023 existen 3 pasajes para Sucre",
            "Chile,15,15,1,2023,el dia Viernes 15 Enero 2023 existen 15 pasajes para Chile",
            "Colombia,15,15,2,2023,el dia Viernes 15 Febrero 2023 existen 15 pasajes para Colombia",
            "Argentina,15,15,5,2023,el dia Viernes 15 Mayo 2023 existen 15 pasajes para Argentina",
            "Cuba,15,15,6,2023,el dia Viernes 15 Junio 2023 existen 15 pasajes para Cuba"
    })
    public void verifyReservaVuelo(String destino, int cantidad, int dia, int mes, int gestion, String expectedResult) throws Exception {
        try (MockedStatic<VerificadorPasajes> mockPasajes = Mockito.mockStatic(VerificadorPasajes.class);
             MockedStatic<VerificadorDias> mockDias = Mockito.mockStatic(VerificadorDias.class)) {

            mockPasajes.when(() -> VerificadorPasajes.existenPasajes("Santa Cruz", 10)).thenReturn(false);
            mockPasajes.when(() -> VerificadorPasajes.existenPasajes("La Paz", 2)).thenReturn(true);
            mockPasajes.when(() -> VerificadorPasajes.existenPasajes("Cochabamba", 5)).thenReturn(true);
            mockPasajes.when(() -> VerificadorPasajes.existenPasajes("Beni", 15)).thenReturn(true);
            mockPasajes.when(() -> VerificadorPasajes.existenPasajes("Pando", 21)).thenReturn(true);
            mockPasajes.when(() -> VerificadorPasajes.existenPasajes("Tarija", 21)).thenReturn(true);
            mockPasajes.when(() -> VerificadorPasajes.existenPasajes("Potosi", 2)).thenReturn(true);
            mockPasajes.when(() -> VerificadorPasajes.existenPasajes("Sucre", 3)).thenReturn(true);
            mockPasajes.when(() -> VerificadorPasajes.existenPasajes("Chile", 15)).thenReturn(true);
            mockPasajes.when(() -> VerificadorPasajes.existenPasajes("Colombia", 15)).thenReturn(true);
            mockPasajes.when(() -> VerificadorPasajes.existenPasajes("Argentina", 15)).thenReturn(true);
            mockPasajes.when(() -> VerificadorPasajes.existenPasajes("Cuba", 15)).thenReturn(true);

            mockDias.when(() -> VerificadorDias.getDay(29, 5, 2023)).thenReturn("Lunes");
            mockDias.when(() -> VerificadorDias.getDay(6, 2, 2023)).thenReturn("Martes");
            mockDias.when(() -> VerificadorDias.getDay(20, 7, 2023)).thenReturn("Miercoles");
            mockDias.when(() -> VerificadorDias.getDay(21, 4, 2023)).thenReturn("Jueves");
            mockDias.when(() -> VerificadorDias.getDay(15, 4, 2023)).thenReturn("Viernes");
            mockDias.when(() -> VerificadorDias.getDay(2, 2, 2023)).thenReturn("Sabado");
            mockDias.when(() -> VerificadorDias.getDay(3, 3, 2023)).thenReturn("Domingo");
            mockDias.when(() -> VerificadorDias.getDay(15, 1, 2023)).thenReturn("Viernes");
            mockDias.when(() -> VerificadorDias.getDay(15, 2, 2023)).thenReturn("Viernes");
            mockDias.when(() -> VerificadorDias.getDay(15, 5, 2023)).thenReturn("Viernes");
            mockDias.when(() -> VerificadorDias.getDay(15, 6, 2023)).thenReturn("Viernes");

            String actualResult = aereolinea.reservaVuelo(destino, cantidad, dia, mes, gestion);
            Assertions.assertEquals(expectedResult, actualResult);
        }
    }

    @ParameterizedTest
    @CsvSource({
            "Santa Cruz,2,-2,2,2022",
            "Cochambamba,3,15,-4,2022",
            "Peru,10,15,10,-4",
            "Pando,68,31,2,2022",
            "Dubai,-40,29,3,2022"
    })
    public void verifyReservaVueloException(String destino, int cantidad, int dia, int mes, int gestion) throws Exception {
        Assertions.assertThrows(Exception.class, () -> {
            aereolinea.reservaVuelo(destino, cantidad, dia, mes, gestion);
        });
    }
}
