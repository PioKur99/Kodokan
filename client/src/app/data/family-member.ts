export enum Relationship {
    rodzic = "Rodzic",
    dziecko = "Dziecko",
    rodzenstwo = "Rodzeństwo",
    malzenstwo = "Małżeństwo"
};

export class FamilyMember {
    firstName: String;
    lastName: String;
    identificationNumber: String;
    relationship: Relationship;
}
