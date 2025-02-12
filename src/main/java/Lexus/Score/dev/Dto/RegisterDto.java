package Lexus.Score.dev.Dto;

import Lexus.Score.dev.Entity.User.UserRole;

public record RegisterDto(String login, String password, UserRole role) {
}
