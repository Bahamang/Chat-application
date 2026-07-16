export interface Profile {
  id: number;
  userId: number;
  username: string;
  photo: any;
  photoId: number;
  description: string;
  language: string;
  friendsId: string;
  occupation: string;
  firstName: string;
  lastName: string;
  birthDate: Date;
  registrationDate: Date;
  lastLogin: Date;
  isActive: boolean;
  created: Date;
  updated: Date;
  disabled: boolean;
  deleted: boolean;
}
