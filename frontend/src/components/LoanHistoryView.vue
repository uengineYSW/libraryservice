<template>

    <v-data-table
        :headers="headers"
        :items="loanHistory"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'LoanHistoryView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "id", value: "id" },
            ],
            loanHistory : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/loanHistories'))

            temp.data._embedded.loanHistories.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.loanHistory = temp.data._embedded.loanHistories;
        },
        methods: {
        }
    }
</script>

