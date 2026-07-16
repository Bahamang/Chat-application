export interface Chat {
  id: number;
  firstUsersId: number;
  secondUsersId: number;
  lastMessage: string;
  lastMessageTime: Date;
  profile: any;
  created: Date;
  updated: Date;
  disabled: boolean;
  deleted: boolean;
}
