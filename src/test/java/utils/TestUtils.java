package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Assert;
import org.springframework.data.domain.PageRequest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Set;

public final class TestUtils {

    private final static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    private final static ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

    public static final PageRequest DEFAULT_PAGEREQUEST = PageRequest.of(0, 10);

    public static <T> void testJavaxValidation(T objeto, int violationCount) {
        Set<ConstraintViolation<T>> violations = validator.validate(objeto);
        Assert.assertEquals(violations.size(), violationCount);
        for (ConstraintViolation<T> violation : violations) {
            System.out.print("\033[1;32m");
            System.out.println("\t\t\t\t\t\t  Propriedade: " + violation.getPropertyPath().toString() + "\t\t Mensagem: " + violation.getMessage());
            System.out.print("\033[0m");
        }
    }

    public static <T> T getMockObject(String mockFolder, String fileName, Class<T> targetClazz) {
        String filePath = mockFolder + "/" + fileName;
        try (InputStream is = TestUtils.class.getResourceAsStream(filePath)) {
            return mapper.readValue(is, targetClazz);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Um erro ocorreu ao carregar o JSON de teste: " + filePath);
        }
    }

    public static void assertLocalDateTime(LocalDateTime localDateTimeExpected, LocalDateTime localDateTimeActual) {
        LocalDateTime expectedMinus2Min = localDateTimeExpected.minusMinutes(2);
        LocalDateTime expectedPlus2Min = localDateTimeExpected.plusMinutes(2);

        Assert.assertTrue(localDateTimeActual.isAfter(expectedMinus2Min) && localDateTimeActual.isBefore(expectedPlus2Min));

    }

    public static File getFile(String pathName) throws UnsupportedEncodingException {
        String path = TestUtils.class.getResource(pathName).toString().replace("file:/", "");
        return new File(URLDecoder.decode(path, StandardCharsets.UTF_8.displayName()));
    }
}
