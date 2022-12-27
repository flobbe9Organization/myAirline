import { AppUserRole } from "./appUserRole.mjs";

export interface AppUser {
    email: string,
    password: string,
    role: AppUserRole
    firstName: string, 
    lastName: string,
    birthday: Date
}