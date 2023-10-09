package com.furiosaming.walletService.service.response;

import com.furiosaming.walletService.service.constants.AppConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponseDto<T> {

    private boolean status;
    private String description;
    private T result;

    public static class Builder<T> {

        private BaseResponseDto<T> baseResponseDto;
        public Builder() {
            baseResponseDto = new BaseResponseDto<>();
        }

        public Builder<T> success(T result) {
            baseResponseDto.status = true;
            baseResponseDto.description = AppConstants.success;
            baseResponseDto.result = result;
            return this;
        }

        public Builder<T> notFound(String description) {
            baseResponseDto.status = false;
            baseResponseDto.description = description;
            baseResponseDto.result = null;
            return this;
        }

        public Builder<T> missing(String description) {
            baseResponseDto.status = false;
            baseResponseDto.description = description;
            baseResponseDto.result = null;
            return this;
        }

        public Builder<T> alreadyExist() {
            baseResponseDto.status = false;
            baseResponseDto.description = AppConstants.alreadyExists;
            baseResponseDto.result = null;
            return this;
        }

        public BaseResponseDto<T> build() {
            return baseResponseDto;
        }
    }
}
