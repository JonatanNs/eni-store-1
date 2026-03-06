import { IRole } from "./IRole";

export interface IUser{
    id? : number | string;
    firstName : string;
    lastName : string;
    email : string;
    password: string;
    roles : IRole;
}