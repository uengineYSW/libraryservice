<template>
    <v-card outlined style="border-radius: 0; margin-top:-10px; padding-bottom:10px;">
        <v-card-title>
            대출 상세
        </v-card-title>
    
        <v-card-text style="margin-top:-30px;">
            <v-row no-gutters style="margin: 15px 0px -30px -15px;">
                <v-col style="max-width:140px;">
                    <Number class="attributes-list" label="LoanId" v-model="value.parameters.loanId" :editMode="editMode"/>
                </v-col>
                <v-col style="max-width:140px;">
                    <Member offline label="Member" v-model="value.parameters.member" :editMode="editMode" @change="change"/>
                </v-col>
                <v-col style="max-width:140px;">
                    <LoanPeriod offline label="LoanPeriod" v-model="value.parameters.loanPeriod" :editMode="editMode" @change="change"/>
                </v-col>
                <v-col style="max-width:140px;">
                    <Date class="attributes-list" label="LoanDate" v-model="value.parameters.loanDate" :editMode="editMode"/>
                </v-col>
                <v-col style="max-width:140px;">
                    <Date class="attributes-list" label="ReturnDueDate" v-model="value.parameters.returnDueDate" :editMode="editMode"/>
                </v-col>
                <v-col style="max-width:140px;">
                    <LoanStatus offline label="Status" v-model="value.parameters.status" :editMode="editMode" @change="change"/>
                </v-col>
                <v-col>
                    <v-btn class="gs-query-search-btn contrast-primary-text"
                        @click="search"
                        small
                        color="primary"
                    >
                        <v-icon small>mdi-magnify</v-icon>검색
                    </v-btn>
                </v-col>
            </v-row>
        </v-card-text>
    </v-card>
</template>

<script>
    export default {
        name: 'LoanDetails',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {
                apiPath: 'loans/search/findByLoanDetails',
                parameters: {}
            },
        }),
        created() {
        },
        watch: {
        },
        methods: {
            search() {
                let search = null;
                search = this.value;
                this.$emit('search', search);
            },
            close() {
                this.$emit('closeDialog');
            },
            change() {
                this.$emit('input', this.value);
            },
        }
    }
</script>
<style>
.attributes-list{
    margin-left: 15px;
}
</style>

