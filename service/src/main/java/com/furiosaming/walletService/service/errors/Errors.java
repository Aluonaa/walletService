package com.furiosaming.walletService.service.errors;

public class Errors {

    public static String PERSON_NOT_FOUND = "Пользователь не найден";



    public static ProgramError notFound(String message) {
        com.furiosaming.walletService.service.errors.ProgramError programError = new ProgramError();
        programError.setType(CommonErrorTypes.NOT_FOUND);
        programError.setMessage(message);
        programError.setCode(null);
        return programError;
    }

    public static ProgramError missing(String message) {
        com.furiosaming.walletService.service.errors.ProgramError programError = new ProgramError();
        programError.setType(CommonErrorTypes.VALIDATION);
        programError.setMessage(message);
        programError.setCode(null);
        return programError;
    }

}
